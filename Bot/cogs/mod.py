import discord,time
from discord.ext import commands
from discord.ext.commands.core import has_permissions



class mod(commands.Cog):
    """
    Moderator Commands
    """
    def __init__(self, client):
        self.client = client
    
    @commands.command()
    async def whois(self,ctx, *, user: discord.Member = None):
        "Gets Person Info"
        if user is None:
            user = ctx.author      
        date_format = "%a, %d %b %Y %I:%M %p"
        embed = discord.Embed(color=0xFF0000, description=user.mention)
        embed.set_author(name=str(user), icon_url=user.avatar_url)
        embed.set_thumbnail(url=user.avatar_url)
        embed.add_field(name="Joined Server", value=user.joined_at.strftime(date_format), inline=False)
        members = sorted(ctx.guild.members, key=lambda m: m.joined_at)
        embed.add_field(name="Join Position", value=str(members.index(user)+1), inline=False)
        embed.add_field(name="Joined Discord", value=user.created_at.strftime(date_format), inline=False)
        if len(user.roles) > 1:
            role_string = ' '.join([r.mention for r in user.roles][1:])
            embed.add_field(name="Roles [{}]".format(len(user.roles)-1), value=role_string, inline=False)
        embed.add_field(name="Perms",value = [perm[0] for perm in ctx.author.guild_permissions if perm[1]])
        embed.set_footer(text='ID: ' + str(user.id))
        return await ctx.send(embed=embed)
    
    @commands.command()
    @has_permissions(administrator=True)
    async def warn(self, ctx, target=None,*,reason="None Given"):
        "Warns Certain Players"
    
        if target == None:
            await ctx.send("Failed Wrong Target")
        else:

            embed = discord.Embed(name="Warning",color=0xFF0000, description=ctx.author.mention)
            embed.set_author(name=ctx.author,icon_url=ctx.author.avatar_url)
            embed.add_field(name="Warned", value=target,inline=False)
            embed.add_field(name="Reason",value=reason, inline=False)
            channel = self.client.get_channel(872664707436261406)
            return await channel.send(embed=embed)
    
    @commands.command()
    @has_permissions(manage_messages=True)
    async def purge(self,ctx,number=5):
        embed = discord.Embed(name="Purge",color=0xFF0000, description=ctx.author.mention)
        embed.add_field(name="Deleting Messages", value=number,inline=False)
        await ctx.send(embed=embed)
        time.sleep(0.5)
        await ctx.channel.purge(limit=(int(number)+1))

        

        
def setup(client):
    client.add_cog(mod(client))