package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.databasemanager.FileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatMessage implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        this.saveChat(player.getDisplayName(), message);
    }

    private void saveChat(String playerDisplayName, String message){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        String timeString = timeFormat.format(date);
        String fileData = FileManager.fileread("\\chat-logs\\"+ dateString + ".txt");

        if (fileData.equals("An error occurred.")){
            String saveText = timeString +"-"+ playerDisplayName + "-"+ message + "\n";
            FileManager.filewrite("\\chat-logs\\"+ dateString + ".txt",saveText);
        }else{
            String saveText = fileData + timeString +"-"+ playerDisplayName + "-"+ message + "\n";
            FileManager.filewrite("\\chat-logs\\"+ dateString + ".txt",saveText);
        }
    }


}
