About this project:

# Carrito de compras:
 Esta aplicacion expone servicios REST para dar de alta:
   
   * Creacion de productos con sus diferentes operaciones CRUD
   * Agregar productos a un carrito con sus diferentes operaciones CRUD y sus respectivas validaciones.
#
La aplicacion se encuentra empaquedata en un .jar, debe ubicarse en la raiz del proyecto y ejecutar el siguiente comando:

java -jar demo-0.0.1-SNAPSHOT.jar   

# Endpoint de items
Method: POST

URL: http://localhost:8080/shopping/

{
  "name": "name",
  "sku": "sku01",
  "description": "anydescription",
  "price": 500.0,
  "amount": 1500,
  "withDiscount": false
}

#dev: 
Wilkeins Valera

 