/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.mongodb.client.FindIterable;
import control.NeoTecnology;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.bson.Document;

/**
 * REST Web Service
 *
 * @author nataliahv
 */
@Path("ws_neotecnology")
public class Ws_neotecnology {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Ws_neotecnology
     */
    public Ws_neotecnology() {
    }
    
    @GET
    @Path("consultarClientes")
    @Produces({"application/json"})
    public String consultarClientes(){
        
        NeoTecnology miNeoTecnology = new NeoTecnology();
        return miNeoTecnology.consultarClientes();
    }
    
   // @GET
  //  @Path("eliminarUnDocumento/id/{id}")
 //   @Produces({"application/json"})
 //   public void eliminarUnDocumento(@PathParam("id")String id){
        
  //      NeoTecnology miNeoTecnology = new NeoTecnology();
  //      miNeoTecnology.eliminarUnDocumento(id);
  ///  }
    
    @GET
    @Path("eliminarDocumento/idEliminar/{idEliminar}")
    @Produces({"application/json"})
    public String eliminarDocumento(@PathParam("idEliminar") String idEliminar)  {
        NeoTecnology miNeotecnology = new NeoTecnology();

        return miNeotecnology.eliminarDocumento(idEliminar);
        
    }


    
    @GET
    @Path("consultarUltimoDocumento")
    @Produces({"application/json"})
    public String consultarUltimoDocumento(){
        
        NeoTecnology miNeoTecnology = new NeoTecnology();
        return miNeoTecnology.consultarUltimoDocumento();
    }
    @GET
    @Path("consultarCincoUltimos")
    @Produces({"application/json"})
    public String consultarCincoUltimos(){
        
        NeoTecnology miNeoTecnology = new NeoTecnology();
        return miNeoTecnology.consultarCincoUltimos();
    }
   //servicio actualizar
  @GET
  @Path("actualizarUnDocumento/id/{id}/nombre/{nombre}/cédula/{cédula}")
  @Produces({"application/json"})
  public void actualizarUnDocumento(@PathParam("id") String id, @PathParam("nombre") String nombre , @PathParam("cédula")String cédula){
     NeoTecnology miNeoTecnology = new NeoTecnology();
     miNeoTecnology.actualizarUnDocumento(id, nombre, cédula);  
}
}
