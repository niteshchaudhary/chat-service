//package com.ua.chat.db;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.CollectionType;
//import com.google.common.base.Charsets;
//import com.google.common.io.Resources;
//import com.ua.chat.api.Message;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import javax.sql.DataSource;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class ChatDB {
//  //  public String DATA_SOURCE = "com/ua/chat/db/dummy_data.json";
//
//
//
//    private List<Message> messageList;
//
//    public ChatDB() throws ParseException {
//        try {
//            initData();
//        } catch (IOException e) {
//            throw new RuntimeException(
//                    //DATA_SOURCE + " missing or is unreadable", e);
//                    "missing or is unreadable");
//        }
//    }
//
//    private void initData() throws IOException, ParseException {
//        JSONParser parser = new JSONParser();
//
//
//        Object object = parser
//                .parse(new FileReader("dummy_data.json"));
//
//
//        System.out.println(object);
//        JSONArray jsonObject = (JSONArray) object;
//
//       // URL url = Resources.getResource(DATA_SOURCE);
//       // String json = Resources.toString(url, Charsets.UTF_8);
//
//       // FileReader fileReader = new FileReader("com/ua/chat/db/dummy_data.json");
//        ObjectMapper mapper = new ObjectMapper();
//        CollectionType type = mapper
//                .getTypeFactory()
//                .constructCollectionType(List.class, Message.class);
//        messageList = mapper.readValue(String.valueOf(jsonObject), type);
//    }
//
//    public List<Message> findAll() {
//        return messageList;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
////    public static HashMap<Integer, Message> messages = new HashMap<>();
////    static{
////        messages.put(1, new Message(1));
////        messages.put(2, new Message(1));
////        messages.put(3, new Message(1));
////    }
////
////    public static List<Message> getMessages(){
////        return new ArrayList<Message>(messages.values());
////    }
////
////    public static Message getMessage(Integer id){
////        System.out.println("id " +1);
////        System.out.println("id " +messages.get(id));
////
////        return messages.get(id);
////    }
////
//////    public static void updateMessage(Integer id, Message employee){
//////        messages.put(id, employee);
//////    }
////
////    public static void deleteMessage(Integer id){
////        messages.remove(id);
////    }
////}
