import psycopg2

#Conexion a la base de datos

conexion = psycopg2.connect(user = 'postgres',
                            password ='C.l.f3012',
                            host = 'localhost',
                            port ='5432',
                            database = 'lavadero_luissalgado')

cursor =conexion.cursor()


sql = 'INSERT INTO clientes (nombre,apellido,tipoDocumento,numeroDocumento,fechaNacimiento,genero,correoElectronico,direccion,telefono) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)'

nombre = input('Ingrese nombre: ')
apellido =input('Ingrese apellido: ')
tipoDocumento = input ('Ingrese Documento: ')
numeroDocumento = input ('Ingrese  numero: ')
fechaNacimiento = input ('Ingrese Fecha: ')
genero = input ('Ingrese genero: ')
correoElectronico = input ('Ingrese correo: ')
direccion = input ('Ingrse direccion: ')
telefono= input('Ingrese telefono: ')


datos = (nombre,apellido,tipoDocumento,numeroDocumento,fechaNacimiento,genero,correoElectronico,direccion,telefono)

cursor.execute(sql,datos)

conexion.commit()

registros = cursor.rowcount

print(f'Registro insertado: {registros}' )

cursor.close()
conexion.close()


