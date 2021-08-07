import discord
from discord.ext import commands
import mysql.connector
from mysql.connector import errorcode

def getString(cnx,UserId, column,tablename):
    
    cursor =  cnx.cursor()
       
    
    Query  = (f"SELECT  {column}  FROM {tablename} WHERE userid={UserId}")
    cursor.execute(Query)
    data = []
    for item in cursor:
        data.append(item)
    cursor.close()
    return data

def setString(cnx,UserId, column,tablename,value):
    
    cursor =  cnx.cursor()
       
    
    Query  = (f"UPDATE {tablename} SET {column}={value} WHERE userid={UserId}")
    cnx.commit()
    cursor.execute(Query)
    cursor.close()


class economy(commands.Cog):
    """
    Indrobot Economy
    """
    def __init__(self,client):
        self.client = client

        self.cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                              host='indrocraft.hopto.org',
                              database='discord',port=3307)
    
    @commands.command(aliases = ["bal"])
    async def balance(self,ctx,target: discord.Member = None):
        "Gets Your Balance"
        if target == None:
            id = ctx.author.id
        else:
            id= target.id
        wallet = getString(self.cnx,id,"wallet","userinfo")       
        wallet = wallet[0][0]
        bank = getString(self.cnx,id,"bank","userinfo")       
        bank = bank[0][0]
        embedVar = discord.Embed(title=f"{ctx.author.name} Balance", description="", color= 0x00ff00)
        embedVar.add_field(name="Wallet", value=wallet, inline=False)
        embedVar.add_field(name="Bank", value=bank, inline=False)
        await ctx.send(embed=embedVar)

    @commands.command()
    async def setmoney(self,ctx,amount,target: discord.Member = None):
        "Dev Command" 
        if ctx.author.id ==574766527098912769:
            if target == None:
                id = ctx.author.id
            else:
                id= target.id
            try:
                int(amount)
                setString(self.cnx,id,"wallet","userinfo",amount)
                await ctx.send("Success")
            except ValueError:
                await ctx.send("Set A Number")
        else:
            await ctx.send("You Dont Have Permision To Do This")

        
    @commands.command()
    async def withdraw(self,ctx,amount):
        if target == None:
            id = ctx.author.id
        else:
            id= target.id
        try:
            int(amount)
            bankmoney =getString(self.cnx,id,"bank","userinfo")
            bankmoney = bankmoney[0][0]
            if int(bankmoney)<int(amount):
                return await ctx.send("Not Enough Money")
            else:
                walletmoney =getString(self.cnx,id,"bank","userinfo")
                walletmoney = int(walletmoney[0][0])
                amount = walletmoney + int(amount)
                setString(self.cnx,id,"wallet","userinfo",amount)
                await ctx.send("Success")
        except ValueError:
            await ctx.send("Error - Contact Dev")
        
    @commands.command()
    async def deposit(self,ctx,amount):
        
        
        id= ctx.author.id
        try:
            int(amount)
            walletmoney =getString(self.cnx,id,"wallet","userinfo")
            walletmoney = walletmoney[0][0]
            if int(walletmoney)<int(amount):
                return await ctx.send("Not Enough Money")
            else:
                bankmoney =getString(self.cnx,id,"bank","userinfo")
                bankmoney = int(bankmoney[0][0])
                amount = bankmoney + int(amount)
                setString(self.cnx,id,"bank","userinfo",amount)
                await ctx.send("Success")
        except ValueError:
            await ctx.send("Error - Contact Dev")
        



        
def setup(client):
    client.add_cog(economy(client))

