import os

import discord
from discord.ext import commands

client = commands.Bot(command_prefix = "$",help_command=None)
client.remove_command('help')


class HelpCommand(commands.MinimalHelpCommand):
    async def send_pages(self):
        destination = self.get_destination()
        for page in self.paginator.pages:
            emby = discord.Embed(description=page)
            await destination.send(embed=emby)



client.help_command = HelpCommand()

@client.event
async def on_ready():
    print('Connected To Discord')
    await client.change_presence(status= discord.Status.idle, activity=discord.Game("Minecraft"))

@client.command()
async def reloadCog(ctx,args=None):
    "Developer Command"
    if ctx.author.id ==  574766527098912769:
        if args == None:
            await ctx.send("You Need An Argument")
        else:
            client.reload_extension(f"cogs.{args}.py")
            await ctx.send("Reload Complete")
    else:
        await ctx.send("Action Failed")



for filename in os.listdir("./cogs"):
    if filename.endswith('.py'):
        client.load_extension(f'cogs.{filename[:-3]}' )



client.run("ODcyMzIxOTE4MDQzMjkxNjU5.YQoLGA.bzufvkkPSQ-vc9fs8Crh6T53QVQ")   

