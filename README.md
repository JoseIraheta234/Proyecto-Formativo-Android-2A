'''Oracle

create table tbDetallesPacientes(
id_DetallePaciente number primary key,
nombre_Paciente varchar2(40) not null,
apellido_Paciente varchar2(40)not null,
edad number not null
);

create table tbCamas(
id_Camas number primary key,
numero_Camas number not null
);


create table tbHabitaciones (
id_Habitacion number primary key,
numero_Habitacion number not null
);


create table tbMedicamentos(
id_Medicamento number primary key,
nombre_Medicamento varchar2(70) Not null

);


create table tbEnfermedades(
id_Enfermedad number primary key,
nombre_Enfermedad varchar2(60) not null
);



create table tbPacientes(
id_Paciente number primary key,
id_DetallePaciente number not null,
id_Habitacion number not null,
id_Medicamento number not null,
id_Enfermedad number not null,
id_Camas number not null,
HoraMedicamento varchar2(100) not null,

constraint fk_Id_Detalle_Paciente foreign key (id_DetallePaciente) references tbDetallesPacientes(id_DetallePaciente),

constraint fk_id_Habitacion foreign key (id_Habitacion) references tbHabitaciones(id_Habitacion),

constraint fk_id_Medicamentos foreign key (id_Medicamento) references tbMedicamentos(id_Medicamento),

constraint fk_id_Enfermedad foreign key (id_Enfermedad) references tbEnfermedades(id_Enfermedad),

constraint fk_id_Camas foreign key (id_Camas) references tbCamas(id_Camas)
);


insert into tbDetallesPacientes values(1,'Paco','Martinez',20)

insert into tbCamas values(1,1)

insert into tbHabitaciones values(1,1)

insert into tbMedicamentos values(1,'paracetamol')

insert into tbEnfermedades values(1,'gripe')


'''
