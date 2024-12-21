import psycopg2

# Conexión a la base de datos
conexion = psycopg2.connect(
    user='postgres',
    password='C.l.f3012',
    host='localhost',
    port='5432',
    database='lavadero_luissalgado'
)

cursor = conexion.cursor()

# Consulta SQL corregida
sql = '''UPDATE clientes 
         SET nombre = %s, apellido = %s, tipodocumento = %s, numerodocumento = %s, 
             fechanacimiento = %s, genero = %s, correoelectronico = %s, 
             direccion = %s, telefono = %s 
         WHERE clienteID = %s'''

# Recolectar datos del usuario
clienteID = input('Ingrese ID para editar: ')
nombre = input('Ingrese nombre: ')
apellido = input('Ingrese apellido: ')
tipoDocumento = input('Ingrese tipo de documento: ')
numeroDocumento = input('Ingrese número de documento: ')
fechaNacimiento = input('Ingrese fecha de nacimiento (YYYY-MM-DD): ')
genero = input('Ingrese género: ')
correoElectronico = input('Ingrese correo electrónico: ')
direccion = input('Ingrese dirección: ')
telefono = input('Ingrese teléfono: ')

# Empaquetar datos
datos = (nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento,
         genero, correoElectronico, direccion, telefono, clienteID)

# Ejecutar la consulta
cursor.execute(sql, datos)

# Confirmar la transacción
conexion.commit()

# Obtener el número de registros afectados
actualizacion = cursor.rowcount

# Imprimir resultado
print(f'Registro actualizado: {actualizacion}')

# Cerrar cursor y conexión
cursor.close()
conexion.close()