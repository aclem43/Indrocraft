import os,time,datetime
import mysql.connector
from mysql.connector import errorcode
import discord
from discord.ext.tasks import loop
from discord.ext import commands
from mysql.connector.errors import Error

intents = discord.Intents.default()
intents.members = True

client = commands.Bot(command_prefix = "$",help_command=None,intents=intents)
client.remove_command('help')


add_user = ("INSERT INTO userinfo "
                "(userid, guildcreate,bank,wallet) "
                "VALUES (%s, %s,%s,%s)")
    
first_run = True

get_all_users  = (f"SELECT  userid  FROM userinfo")

get_all_complaints = (f"SELECT NAME,ComplaintMessage FROM playerinfo where ComplaintMessage <> ''")
message = None
@loop(minutes=5)
async def updateComplaints():
    global message,first_run
    cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                            host='indrocraft.hopto.org',
                            database='indrocraft',port=3307)
    cursor =  cnx.cursor()
    cursor.execute(get_all_complaints)
    allComplaints = []
    for item in cursor:
        allComplaints.append(item)
    cursor.close()
    now = datetime.datetime.now()
    channel = client.get_channel(873519168035622923)
    message = str(allComplaints)+"\n"+str(now.strftime("%Y-%m-%d %H:%M:%S"))
    if first_run:
        message = await channel.send(message)
        first_run = False
    else:
        await message.edit(message)
    print("Task-1 Cmplete")


    
@client.event
async def on_ready(): 
    updateComplaints.start()
    print('Connected To Discord')
    await client.change_presence(status= discord.Status.idle, activity=discord.Game("Minecraft"))
    cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                            host='indrocraft.hopto.org',
                            database='discord',port=3307)
    
    
    guilds = client.guilds
    startloop = time.time()
    cursor =  cnx.cursor()
    cursor.execute(get_all_users)
    allUsers = []
    for item in cursor:
        allUsers.append(item[0])

    for guild in guilds:
        for member in guild.members:
            if str(member.id) not in allUsers:
                newUser = (member.id,0,0,0)
                try:
                    cursor.execute(add_user, newUser,)
                except mysql.connector.Error  as e:
                    print(e)
            else:
                allUsers.remove(str(member.id))

    
    endloop = time.time()
    print(endloop - startloop)
    cursor.close()
    cnx.commit()
    cnx.close()
    print("Ready")
    

            
@client.event
async def on_member_join(member):
    cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                            host='indrocraft.hopto.org',
                            database='discord',port=3307)
    cursor =  cnx.cursor()
    newUser = (member.id,0,0)
    cursor.execute(add_user, newUser)
    cursor.close()
    cnx.commit()
    cnx.close()


@client.command()
async def unloadCog(ctx,args=None):
    "Developer Command"
    if ctx.author.id ==  574766527098912769:
        if args == None:
            await ctx.send("You Need An Argument")
        else:   
            client.unload_extension(f"cogs.{args}")
            await ctx.send("Reload Complete")
    else:
        await ctx.send("Action Failed")

@client.command()
async def loadCog(ctx,args=None):
    "Developer Command"
    if ctx.author.id ==  574766527098912769:
        if args == None:
            await ctx.send("You Need An Argument")
        else:   
            client.load_extension(f"cogs.{args}")
            await ctx.send("Reload Complete")
    else:
        await ctx.send("Action Failed")



for filename in os.listdir("./cogs"):
    if filename.endswith('.py'):
        client.load_extension(f'cogs.{filename[:-3]}')







client.run("ODcyNzY1NDIxMTEwMDUwODM2.YQuoIw.zSnPrVTF2JkNdeIH6Ap9EfkZpIA")   

