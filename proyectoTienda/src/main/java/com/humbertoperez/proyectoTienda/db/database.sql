drop database if exists proyectoTienda_db_in5bm;
create database proyectoTienda_db_in5bm;
use proyectoTienda_db_in5bm;

create table usuario(
codigo_usuario int primary key auto_increment,
username varchar(45),
password varchar(45),
email varchar(60),
rol varchar(45),
estado boolean
);

create table producto(
codigo_producto int primary key auto_increment,
nombre_producto varchar(60),
precio decimal(10,2),
stock int,
estado boolean
);

create table clientes(
dpi_cliente int primary key,
nombre_cliente varchar(50),
apellido_cliente varchar(50),
direccion varchar(100),
estado boolean
);

create table ventas(
codigo_venta int primary key auto_increment,
fecha_venta date,
total decimal(10,2),
estado boolean,
dpi_cliente int,
codigo_usuario int,
foreign key (dpi_cliente) references clientes(dpi_cliente),
foreign key (codigo_usuario) references usuario(codigo_usuario)
);

create table detallesVenta(
codigo_detalle_venta int primary key auto_increment,
cantidad int,
total decimal(10,2),
subtotal decimal(10,2),
codigo_producto int,
codigo_venta int,
foreign key (codigo_producto) references producto(codigo_producto),
foreign key (codigo_venta) references ventas(codigo_venta)
);




