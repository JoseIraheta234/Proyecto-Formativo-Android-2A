```SQL

create table tbDetallesPacientes(
id_DetallePaciente int generated always as identity primary key,
nombre_Paciente varchar2(40) not null,
apellido_Paciente varchar2(40)not null,
edad number not null
);


create table tbCamas(
id_Camas int generated always as identity primary key,
numero_Camas number not null
);


create table tbHabitaciones (
id_Habitacion int generated always as identity primary key,
numero_Habitacion number not null
);


create table tbMedicamentos(
id_Medicamento int generated always as identity primary key,
nombre_Medicamento varchar2(70) Not null
);


create table tbEnfermedades(
id_Enfermedad int generated always as identity primary key,
nombre_Enfermedad varchar2(60) not null
);


create table tbPacientes(
id_Paciente int generated always as identity primary key,
id_DetallePaciente int not null,
id_Habitacion int not null,
id_Medicamento int not null,
id_Enfermedad int not null,
id_Camas int not null,
HoraMedicamento varchar2(100) not null,

constraint fk_Id_Detalle_Paciente foreign key (id_DetallePaciente) references tbDetallesPacientes(id_DetallePaciente),

constraint fk_id_Habitacion foreign key (id_Habitacion) references tbHabitaciones(id_Habitacion),

constraint fk_id_Medicamentos foreign key (id_Medicamento) references tbMedicamentos(id_Medicamento),

constraint fk_id_Enfermedad foreign key (id_Enfermedad) references tbEnfermedades(id_Enfermedad),

constraint fk_id_Camas foreign key (id_Camas) references tbCamas(id_Camas)
);


insert into tbDetallesPacientes(nombre_Paciente,apellido_Paciente,edad)values('Paco','Martinez',20);

insert into tbCamas(numero_Camas)values(1);

insert into tbHabitaciones(numero_Habitacion) values(1);

insert into tbMedicamentos(nombre_Medicamento) values('paracetamol');

insert into tbEnfermedades(nombre_Enfermedad) values('gripe');


SELECT p.id_Paciente, pd.nombre_Paciente,pd.apellido_Paciente, h.numero_Habitacion, m.nombre_Medicamento, e.nombre_Enfermedad,c.numero_Camas, p.HoraMedicamento FROM tbPacientes p INNER JOIN tbDetallesPacientes pd ON p.id_DetallePaciente = pd.id_DetallePaciente INNER JOIN tbCamas c ON p.id_Camas = c.id_Camas INNER JOIN tbHabitaciones h ON p.id_Habitacion = h.id_Habitacion INNER JOIN tbMedicamentos m ON p.id_Medicamento = m.id_Medicamento INNER JOIN tbEnfermedades e ON p.id_Enfermedad = e.id_Enfermedad

```
