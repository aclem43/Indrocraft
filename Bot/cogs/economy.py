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

def setString(cnx,UserId, column,tablename,value,commit=True):
    
    cursor =  cnx.cursor()
       
    Query  = (f"UPDATE {tablename} SET {column}='{value}' WHERE userid='{UserId}'")
    
    cursor.execute(Query)
    cursor.close()
    if commit:
        cnx.commit()
    


class economy(commands.Cog):
    """
    Indrobot Economy
    """
    def __init__(self,client):
        self.client = client

        self.cnx = mysql.connector.connect(user='Bot', password='M@F2j8iPlsTPiIzA',
                              host='indrocraft.hopto.org',
                              database='discord',port=3307)
    
        table = (
        "CREATE TABLE `dailyinfo` ("
        "  `userid` VARCHAR(100),"
        "  `datetimelast` VARCHAR(100),"
        " `streak` INT,"
        "  PRIMARY KEY (`userid`)"
        ")")
        cursor = self.cnx.cursor()
        try:
            cursor.execute(table)
            self.cnx.commit()
        except mysql.connector.Error as err:
            if err.errno == errorcode.ER_TABLE_EXISTS_ERROR:
                print("Table already exists. - Ignore")
            else:
                print(err.msg)

        cursor.close()
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
        "Withdraws Money From Bank"
        id = ctx.author.id

        try:
            int(amount)
            bankmoney =getString(self.cnx,id,"bank","userinfo")
            bankmoney = bankmoney[0][0]
            if int(bankmoney)<int(amount):
                return await ctx.send("Not Enough Money")
            else:
                walletmoney =getString(self.cnx,id,"bank","userinfo")
                walletmoney = int(walletmoney[0][0])
                walletmoney = int(walletmoney) + int(amount)
                bankmoney = int(bankmoney) - int(amount)
                setString(self.cnx,id,"bank","userinfo",bankmoney)
                setString(self.cnx,id,"wallet","userinfo",amount)
                await ctx.send("Success")
        except ValueError:
            await ctx.send("Error - Contact Dev")
        
    @commands.command()
    async def deposit(self,ctx,amount):
        "Deposits Money Into Bank"
        
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
                bankmoney = int(bankmoney) + int(amount)
                walletmoney = int(walletmoney) - int(amount)
                setString(self.cnx,id,"wallet","userinfo",walletmoney)
                setString(self.cnx,id,"bank","userinfo",bankmoney)
                await ctx.send("Success")
        except ValueError:
            await ctx.send("Error - Contact Dev")
        
    @commands.command()
    async def pay(self,ctx,amount,target: discord.Member = None):
        "Sends User Money From Your Wallet"
        if target == None or target.id == ctx.author.id:
            return await ctx.send("Please Select A User To Send")
        else:
            walletmoney =getString(self.cnx,ctx.author.id,"wallet","userinfo")
            walletmoney = int(walletmoney[0][0])
            if walletmoney >= int(amount):
                targetmoney = getString(self.cnx,target.id,"wallet","userinfo")
                self.cnx.commit()
                targetmoney = int(targetmoney[0][0]) + int(amount)
                walletmoney =  walletmoney- int(amount)
                setString(self.cnx,target.id,"wallet","userinfo",targetmoney,False)
                setString(self.cnx,ctx.author.id,"wallet","userinfo",walletmoney,False)
                self.cnx.commit()
                await ctx.send("Transaction Complete")
            else:
                await ctx.send("Transaction Failed - Not Enough Money")
    
    @commands.command()
    async def daily(self,ctx):
        pass



        
def setup(client):
    client.add_cog(economy(client))
