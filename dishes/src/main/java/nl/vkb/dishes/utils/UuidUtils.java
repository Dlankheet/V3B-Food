package nl.vkb.dishes.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UuidUtils {
    private UuidUtils() {}
    public static List<UUID> parseStringToList(String idString){
        String[] dishIds = idString.split(",");
        ArrayList<UUID> dishUUIDs = new ArrayList<>();

        for(String id : dishIds) {
            dishUUIDs.add(UUID.fromString(id));
        }
        return dishUUIDs;
    }

    public static List<String> parseUUIDtoList(List<UUID> list){
        ArrayList<String> resultlist = new ArrayList<>();
        list.forEach(uuid -> resultlist.add(uuid.toString()));
        return resultlist;
    }
    public static String parseUUIDListToString(List<UUID> list){
        StringBuilder string = new StringBuilder();
        for(UUID id : list){
            string.append(id.toString()).append(",");
        }
        return string.toString();
    }
}
