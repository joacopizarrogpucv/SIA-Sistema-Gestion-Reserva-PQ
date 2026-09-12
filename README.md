#  Sistema Gestión Reserva

Sistema de gestión de reservas para un parque, desarrollado en **Java** utilizando Programación Orientada a Objetos.

---

##  Descripción

El proyecto consiste en un sistema que permite administrar un parque y sus distintos recursos, permitiendo a los usuarios realizar, consultar y cancelar reservas.

El sistema trabaja con información cargada desde un archivo `parque.csv` y permite gestionar diferentes tipos de recursos:

- Cabañas
-  Campings
-  Actividades

Además, el sistema controla la disponibilidad de los recursos y evita que se realicen reservas que se superpongan con otras existentes.

---

##  Funcionalidades

###  Usuarios

El sistema permite:

- Crear un usuario manualmente.
- Registrar nombre y RUT.
- Asociar reservas al usuario actual.

###  Reservas

Es posible:

- Crear reservas.
- Consultar reservas mediante su ID.
- Cancelar reservas.
- Verificar disponibilidad.
- Evitar reservas duplicadas.
- Calcular automáticamente el costo de una reserva.

###  Cabañas

Las cabañas poseen:

- Nombre.
- Capacidad.
- Cantidad de habitaciones.
- Control de días ocupados.

###  Campings

Los campings poseen:

- Nombre.
- Capacidad.
- Disponibilidad de agua potable.
- Control de días ocupados.

### 🧗 Actividades

Las actividades poseen:

- Nombre.
- Capacidad.
- Guía.
- Duración.
- Horarios disponibles.

El sistema verifica que los horarios de las actividades no se superpongan.

---

##  Estructura del proyecto

```text
ParquePUCV
│
├──  Main.java
├──  Parque.java
├──  Recurso.java
├──  Cabana.java
├──  Camping.java
├──  Actividad.java
├──  Reserva.java
├──  Usuario.java
├──  Tarifa.java
├──  Permiso.java
│
└──  parque.csv
