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

# Consulta SQL para obtener todos los clientes
sql = '''SELECT * FROM clientes'''

# Ejecutar la consulta
cursor.execute(sql)

# Obtener todos los registros
registros = cursor.fetchall()

# Mostrar los registros
if registros:
    print("Clientes registrados:")
    for registro in registros:
        print(f"ID: {registro[0]}, Nombre: {registro[1]}, Apellido: {registro[2]}, Documento: {registro[3]} {registro[4]}, Fecha Nac: {registro[5]}, Género: {registro[6]}, Correo: {registro[7]}, Dirección: {registro[8]}, Teléfono: {registro[9]}")
else:
    print("No hay clientes registrados.")

# Cerrar cursor y conexión
cursor.close()
conexion.close()