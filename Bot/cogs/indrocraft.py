"""
embedVar = discord.Embed(title="Help Menu", description="Desc", color= 0x00ff00)
embedVar.add_field(name="Help", value="Displays Help Menu", inline=False)
embedVar.add_field(name="Field2", value="hi2", inline=False)
await ctx.send(embed=embedVar)
"""
import mysql.connector

import discord
from discord.ext import commands

def getUUID(cnx,Userid):
    cursor =  cnx.cursor()
    Query  = (f"SELECT UUID FROM discord WHERE NAME={Userid}")
    cursor.execute(Query)
    print(cursor)
    return cursor

#UUID = getUUID(cnx,UserId)
def getString(cnx,UserId, column,tablename):
    
    cursor =  cnx.cursor()
       
    
    Query  = (f"SELECT  {column}  FROM {tablename} WHERE UUID={UserId}")#{UUID}")
    cursor.execute(Query)
    data = []
    for item in cursor:
        data.append(item)
    cursor.close()
    return data

class indrocraft(commands.Cog):
    """All Commands Related To Indrocraft"""
    
    def __init__(self, client):
        self.client = client
        self.cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                              host='indrocraft.hopto.org',
                              database='indrocraft')



        print("Database Connected")


    
    @commands.command()
    async def verify(self,ctx):
        """Links Minecraft Account To Discord - Not Done"""

    @commands.command(aliases=["comp","sugest"])
    async def complain(self,ctx,*,args):
        """Sends Message To Admins Use to send Complaints or Suggestions. Do Not Spam, Your Username Is Sent With The Message"""
        channel = self.client.get_channel(872346356080050256)
        await channel.send(f"{ctx.author}: {args}")
        embedVar = discord.Embed(title="Message Sent", description="", color= 0x00ff00)
        await ctx.send(embed=embedVar)

    @commands.command()
    async def links(self,ctx):
        """Gets Links """
        embedVar = discord.Embed(title="Links", description="", color= 0x00ff00)
        embedVar.add_field(name="Indrocraft", value="indrocraft.hopto.org", inline=False)
        embedVar.add_field(name="Dynmap", value="http://indrocraft.hopto.org:4444/#", inline=False)
        embedVar.add_field(name="Discord Server", value="https://discord.gg/PVQP8UveQg", inline=False)
        await ctx.send(embed=embedVar)

    @commands.command()
    async def count(self,ctx):
        "Gets Different Tallys"
        data = getString(self.cnx,"UUID",'*',"playerinfo")
        embedVar = discord.Embed(title="Counts", description="", color= 0x00ff00)
        embedVar.add_field(name="Total Ever Sever Player Count", value=str(len(data)), inline=False)
        embedVar.add_field(name="Total Users Discord", value=str(ctx.message.guild.member_count), inline=False)
        await ctx.send(embed=embedVar)



    

        

def setup(client):
    client.add_cog(indrocraft(client))