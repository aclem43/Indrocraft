import discord
from discord.ext import commands
from discord.ext.commands.core import guild_only, has_permissions
import mysql.connector
from mysql.connector import errorcode

add_user = ("INSERT INTO userinfo "
               "(userid, guildcreate) "
               "VALUES (%s, %s)")

update_user = (
  "UPDATE userinfo SET guildcreate = %s "
  "WHERE userid = %s")


def getString(cnx,UserId, column,tablename):
    
    cursor =  cnx.cursor()
       
    
    Query  = (f"SELECT  {column}  FROM {tablename} WHERE userid={UserId}")
    cursor.execute(Query)
    data = []
    for item in cursor:
        data.append(item)
    cursor.close()
    return data


class guilds(commands.Cog):
    """
    Guild Commands
    """
    def __init__(self, client):
        self.client = client
        self.cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                              host='indrocraft.hopto.org',
                              database='discord',port=3307)
        cursor =  self.cnx.cursor()

        table = (
        "CREATE TABLE `userinfo` ("
        "  `userid` VARCHAR(100),"
        "  `guildcreate` VARCHAR(10),"
        " `wallet` VARCHAR(100),"
        " `bank` VARCHAR(100),"
        "  PRIMARY KEY (`userid`)"
        ")")
        try:
            cursor.execute(table)
        except mysql.connector.Error as err:
            if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
                print("Table already exists. - Ignore")
            else:
                print(err.msg)

        cursor.close()

    

    @commands.command(aliases = ["createguild"])
    async def createGuild(self,ctx,name=None):
        num = getString(self.cnx,ctx.author.id,"guildcreate","userinfo")

        num = int(num[0][0])
        if num == 1:
            await ctx.send("You Already Have A Guild")
        else:
            guild = ctx.message.guild
            if name==None:
                await ctx.send("Atually Name It Some Thing")
            else:
                category = await guild.create_category(name)
                role = await guild.create_role(name = (name+"-Guild"))
                    
                overwrites = {
                        ctx.guild.default_role: discord.PermissionOverwrite(read_messages=False, connect=False),
                        role: discord.PermissionOverwrite(read_messages=True, send_messages=True, connect=True, speak=True)
                    }

                await category.edit(overwrites=overwrites)
                await guild.create_text_channel(f"{name}-private-chat", category = category, sync_permissions=True)
                await guild.create_voice_channel(f"{name}-private-vc", category = category, sync_permissions=True)

                await ctx.author.add_roles(role)

                user = (1,ctx.author.id)
                cursor = self.cnx.cursor(update_user,user)
                cursor.execute()
                cursor.close()
                self.cnx.commit()
                await ctx.send("Guild Made")



        

        

        
def setup(client):
    client.add_cog(guilds(client))