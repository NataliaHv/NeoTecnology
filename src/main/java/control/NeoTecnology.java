/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import javax.servlet.ServletException;

/**
 *
 * @author nataliahv
 */
public class NeoTecnology {
    String respuesta = null;

    public String consultarClientes() {
        //Creacion del cliente para conectar al servidor remoto
        MongoClient cliente;

        //la uri es una ruta para conectarse en este caso al servidor remoto
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        cliente = new MongoClient(uri);
        //se crea el objeto de type MongoDatabase para conectarse y obtener la informacion de la base de datos que se encuentra en el servidor
        MongoDatabase db;
        db = cliente.getDatabase("lab6");
        //asi se obtiene la colecction y return el la coleccion de nosotros llamada clientes
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("clientes");
        
        try {
          //  MongoCollection<Document> collection = db.getCollection("clientes");
            respuesta += collectionNeoTecnology.find().first().toJson();
            respuesta += "{\"confirmation\" : \"felicidades usted encontro el documento 1\"}";
        } catch (Exception e) {
            respuesta = "{\"cuidado\" : \" no encontro el documento \"}";

        }
        
        return respuesta;
        
    
       // return collectionNeoTecnology.find().first().toJson();

        // try{
        //  int []myNumbers={1}; 
        //   System.out.println(myNumbers[2]);
        //  }catch (Exception e){
        //        System.out.println("{\"Cuidado \":\"1\"}"); 
        //   }finally {
    }
    //public String eliminarUnDocumento(String id) {
    //   MongoClient cliente;
    //   MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
    //   cliente = new MongoClient(uri);

    // MongoDatabase db;
    //  db = cliente.getDatabase("lab6");
    //   MongoCollection<Document> collectionNeoTecnology = db.getCollection("clientes");
    // DeleteResult result = collectionNeoTecnology.deleteOne((Bson) collectionNeoTecnology.deleteOne(eq("_id", new ObjectId(id))));
    //   return "(\"Confirmation\":\"" + result.getDeletedCount() + "\")";
    //}
    public String eliminarDocumento(String idEliminar) {

        MongoClient mongoClient;
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        MongoDatabase db;
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("lab6");
        // MongoCollection<Document> collection = db.getCollection("clientes");
        //  String respuesta = "";
        //String respuesta = null;
        try {
            MongoCollection<Document> collection = db.getCollection("clientes");
            collection.deleteOne(eq("_id", new ObjectId(idEliminar)));
            respuesta = "{\"confirmation\" : \"felicidades usted pudo eliminar el documento\"}";
        } catch (IllegalArgumentException e) {
            respuesta = "{\"cuidado\" : \"el id no esta en la coleccion clientes\"}";

        }
        
        return respuesta;
        
    }

    public String consultarUltimoDocumento() {
        //Creacion del cliente para conectar al servidor remoto
        MongoClient empleado;

        //la uri es una ruta para conectarse en este caso al servidor remoto
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        empleado = new MongoClient(uri);
        //se crea el objeto de type MongoDatabase para conectarse y obtener la informacion de la base de datos que se encuentra en el servidor
        MongoDatabase db;
        db = empleado.getDatabase("lab6");
        //asi se obtiene la ultima colecction y retorna el la coleccion de nosotros llamada empleados
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("empleados");

        FindIterable<Document> mySort = collectionNeoTecnology.find().sort(new BasicDBObject("_id", -1)).limit(1);

        MongoCursor<Document> cursor = mySort.iterator();

        while (cursor.hasNext()) {
            return cursor.next().toJson();
        }

        //return collectionNeoTecnology.find().sort({"_id" : -1}).limit(1).toJson();
        //descending("quantity", "totalAmount") 
        //return collectionNeoTecnology.find().sort(Sorts.descending({"_id", -1).limit(1).toJson();
        //descending("codigo")).first().toJson();
        //return collectionNeoTecnology.find().sort({"_id" : -1}).limit(1).toString();
        return null;
    }

    public String consultarCincoUltimos() {
        //Creacion del cliente para conectar al servidor remoto
        MongoClient cliente;

        //la uri es una ruta para conectarse en este caso al servidor remoto
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        cliente = new MongoClient(uri);
        //se crea el objeto de type MongoDatabase para conectarse y obtener la informacion de la base de datos que se encuentra en el servidor
        MongoDatabase db;
        db = cliente.getDatabase("lab6");
        //asi se obtiene la ultima colecction y retorna el la coleccion de nosotros llamada empleados
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("clientes");

        FindIterable<Document> mySort = collectionNeoTecnology.find().sort(new BasicDBObject("_id", -1)).limit(4);

        MongoCursor<Document> cursor = mySort.iterator();

        String holi_String = "[";
        while (cursor.hasNext()) {
            holi_String = holi_String.concat(cursor.next().toJson());
            holi_String = holi_String.concat(",");

        }

        holi_String = holi_String.concat("]");
        return holi_String;
        //Consumer<Document> printConsumer=new Consumer<Document>(){
        // @Override
        // public String accept (final Document document){
        // System.out.print(document.toJson());
        // return cursor.next().toJson();
    }

    public String actualizarUnDocumento(String id, String nombre, String cédula) {
        //Creacion del cliente para conectar al servidor remoto
        MongoClient empleado;

        //la uri es una ruta para conectarse en este caso al servidor remoto
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        empleado = new MongoClient(uri);
        //se crea el objeto de type MongoDatabase para conectarse y obtener la informacion de la base de datos que se encuentra en el servidor
        MongoDatabase db;
        db = empleado.getDatabase("lab6");
        //asi se obtiene la colecction y return el la coleccion de nosotros llamada clientes
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("empleados");

        collectionNeoTecnology.updateOne(eq("_id,", new ObjectId(id)),
                combine(set("nombre", "Jose Victoria Peña"), set("cédula", "1114567809")));

        // return "{\"Confirmation\":1}";
        return null;

    }

}
