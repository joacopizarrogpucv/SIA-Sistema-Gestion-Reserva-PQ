# Sistema de Gestión de Reservas – Parque PUCV
 
Sistema de gestión de reservas para un parque, desarrollado en **Java** con Programación Orientada a Objetos. Permite administrar recursos del parque (cabañas, campings y actividades) y que los usuarios creen, consulten, modifiquen y cancelen reservas.
 
Se puede usar en **modo consola** o en **modo ventana** (Swing). Al iniciar, el programa pregunta qué modo usar.
 
---
 
## Funcionalidades
 
### Usuarios
- Ingresar con un RUT existente o crear un usuario nuevo (RUT y nombre).
- Listar, buscar, modificar el nombre y eliminar usuarios.
### Reservas
- Reservar un recurso indicando su ID y la cantidad de personas.
- Cancelar una reserva por su ID.
- Listar y buscar las reservas del usuario actual.
- Modificar la cantidad de personas de una reserva.
- Aprobar o rechazar el permiso asociado a cada reserva (nace como *Pendiente*).
- Cálculo automático del costo: precio por persona × cantidad de personas.
### Recursos
| Tipo | Datos propios |
|---|---|
| Cabaña | Cantidad de habitaciones |
| Camping | Si tiene piscina |
| Actividad | Guía y duración |
 
Todos tienen nombre, ID y capacidad máxima. También se pueden buscar por ID o filtrar según la cantidad de personas que soportan.
 
### Persistencia
Los datos se cargan desde archivos CSV al iniciar y los usuarios y reservas se guardan al salir.
 
---
 
## Manejo de excepciones
 
El proyecto define dos excepciones propias, ambas *unchecked* (`RuntimeException`):
 
| Excepción | Cuándo se lanza |
|---|---|
| `CapacidadExcedidaException` | Al reservar más personas de las que admite el recurso |
| `ReservaNoEncontradaException` | Al cancelar una reserva con un ID que no existe |
 
Las excepciones son manejadas mediante bloques try-catch en MenuConsola y MenuVentana. Además, Lector captura CapacidadExcedidaException para evitar que una reserva inválida en el archivo CSV detenga la carga completa del sistema.
 
---
 
## Estructura del proyecto
 
```text
SIA_Reserva
│
├── src/
│   ├── Main.java
│   ├── Parque.java
│   ├── Recurso.java
│   ├── Cabana.java
│   ├── Camping.java
│   ├── Actividad.java
│   ├── Usuario.java
│   ├── Reserva.java
│   ├── Permiso.java
│   ├── Tarifa.java
│   ├── CapacidadExcedidaException.java
│   ├── ReservaNoEncontradaException.java
│   ├── Lector.java
│   ├── Escritor.java
│   ├── MenuConsola.java
│   └── MenuVentana.java
│
├── parque.csv
├── usuario.csv
├── reservas.csv
├── build.xml
└── nbproject/
```
 
---
 
## Formato de los archivos CSV
 
Los campos se separan con `;`. Las líneas vacías o que empiezan con `#` se ignoran.
 
**parque.csv**
```text
tipo;nombre;id;capacidad;dato1;dato2
PARQUE;Parque PUCV;;;Valparaiso;
CABANA;Bosque Norte;CAB1;6;3;          (dato1 = habitaciones)
CAMPING;Camping A;CAM1;6;true;         (dato1 = piscina)
ACTIVIDAD;Senderismo;ACT1;10;Juan Perez;70   (dato1 = guía, dato2 = duración)
```
 
**usuario.csv**
```text
tipo;nombre;rut
USUARIO;Maria Soto;11222333-4
```
 
**reservas.csv**
```text
rut;idRecurso;cantidad;permisoAprobado
11222333-4;ACT1;1;false
```
 
---
 
## Cómo ejecutar
 
Requisitos: **JDK 8 o superior**.
 
```bash
cd src
javac *.java
cd ..
java -cp src Main
```

Ejecuta el programa desde la carpeta donde están los tres archivos `.csv`; si no los encuentra, muestra *"Archivo no encontrado"*.
 
---

### Ejecución en NetBeans

1. Abrir NetBeans.
2. Seleccionar `File > Open Project`.
3. Abrir la carpeta `SIA_Reserva_Prueba`.
4. Verificar que los archivos `parque.csv`, `usuario.csv` y `reservas.csv`
   se encuentren en la raíz del proyecto.
5. Ejecutar el proyecto con `Run Project` o `F6`.
6. Seleccionar `Main` como clase principal si NetBeans lo solicita.

El proyecto fue probado en NetBeans con los archivos CSV ubicados en la raíz
del proyecto.

### Ejecución mediante terminal

Desde la carpeta `src`:

```bash
javac *.java
cd ..
java -cp src Main
```

 
## Menú principal (modo consola)
 
1. Reservar
2. Cancelar reserva
3. Buscar recurso
4. Ingresar / crear usuario
5. Gestionar reservas
6. Gestionar usuarios
7. Buscar recursos por capacidad
8. Salir
Para reservar, cancelar o gestionar reservas primero hay que ingresar como usuario (opción 4).