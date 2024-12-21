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

# Consulta SQL para eliminar un cliente
sql = '''DELETE FROM clientes WHERE clienteID = %s'''

# Recolectar el ID del cliente a eliminar
clienteID = input('Ingrese ID del cliente a eliminar: ')

# Empaquetar datos
datos = (clienteID,)

# Ejecutar la consulta
cursor.execute(sql, datos)

# Confirmar la transacción
conexion.commit()

# Obtener el número de registros afectados
eliminados = cursor.rowcount

# Imprimir resultado
if eliminados > 0:
    print(f'Registro eliminado: {eliminados}')
else:
    print('No se encontró un cliente con ese ID.')

# Cerrar cursor y conexión
cursor.close()
conexion.close()