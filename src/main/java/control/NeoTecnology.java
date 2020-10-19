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
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.MongoException;
import com.mongodb.MongoServerException;
import com.mongodb.client.model.Sorts;
import static javafx.scene.Cursor.cursor;


/**
 *
 * @author nataliahv
 */
public class NeoTecnology {

    String result = null;

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
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("deudores");

        try {
            // MongoCollection<Document> collection = db.getCollection("clientes");
            // respuesta = "{\"confirmation\" :"+collectionNeoTecnology.find().first().toJson()+",\"confirmation\" :";
            result = collectionNeoTecnology.find().first().toJson();
            //  respuesta += "{\"confirmation\" : \"felicidades usted encontro el documento 1\"}}";
        } catch (Exception e) {

            result = "{\"cuidado\" : \" esta vacia ,no encontro el documento 1 de la coleccion \"}";

        }

        return result;

    }

    public String eliminarDocumento(String idEliminar) {

        MongoClient mongoClient;
        MongoClientURI uri = new MongoClientURI("mongodb://userLab6:passworduserLab6@93.188.167.110:27017/?authSource=lab6");
        MongoDatabase db;
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("lab6");
        // MongoCollection<Document> collection = db.getCollection("clientes");
        //  String respuesta = "";

        try {
            MongoCollection<Document> collection = db.getCollection("clientes");
            collection.deleteOne(eq("_id", new ObjectId(idEliminar)));
            result = "{\"confirmation\" : \"felicidades usted pudo eliminar el documento\"}";
        } catch (IllegalArgumentException e) {
            result = "{\"cuidado\" : \"el id no esta en la coleccion clientes\"}";

        }

        return result;

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
        //asi se obtiene la ultima colecction y retorna  la coleccion de nosotros llamada empleados
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("empleados");
        FindIterable<Document> mySort = collectionNeoTecnology.find().sort(new BasicDBObject("_id", -1)).limit(1);
        MongoCursor<Document> cursor = mySort.iterator();
        try {
           
           while (cursor.hasNext()) {
            
            //collectionNeoTecnology.find().sort(Sorts.descending("_Id")).first().toJson();
            result = cursor.next().toJson();//+"{\"confirmation\" : \"se encontro el ultimo documento\"}";
            }
        } catch (Exception e) {
            result = "{\"cuidado\" : \"la coleccion esta vacia\"}";

        }

        return  result;
        //return collectionNeoTecnology.find().sort({"_id" : -1}).limit(1).toJson();
        //descending("quantity", "totalAmount") 
        //return collectionNeoTecnology.find().sort(Sorts.descending({"_id", -1).limit(1).toJson();
        //descending("codigo")).first().toJson();
        //return collectionNeoTecnology.find().sort({"_id" : -1}).limit(1).toString();
      
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
        //asi se obtiene la ultima colecction y retorna el la coleccion de nosotros llamada 
        MongoCollection<Document> collectionNeoTecnology = db.getCollection("empleados");

        int comparador = 0;

        try {
            FindIterable<Document> mySort = collectionNeoTecnology.find().sort(new BasicDBObject("_id", -1)).limit(5);
            MongoCursor<Document> cursor = mySort.iterator();
            while (cursor.hasNext()) {
                if (comparador == 0) {
                    result = "{\"Confirmation\":";
                }
                result = result.concat(cursor.next().toJson());
               

                if (comparador <= 3) {
                    result = result.concat(",\"Confirmation\":");
                } else if (comparador >= 4) {
                    result = result.concat("}");
                }
                comparador++;
            }

        } catch (MongoException e) {
            result = "{\"cuidado\" : \"se paso del rago delos ultimos 5\"}";
            //e.printStackTrace();
        }
        return result;
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

        return "{\"Confirmation\":1}";

    }

}
