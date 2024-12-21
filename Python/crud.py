import tkinter as tk
from tkinter import ttk, messagebox
import psycopg2

# Funciones para CRUD
def conectar_db():
    return psycopg2.connect(
        user='postgres',
        password='C.l.f3012',
        host='localhost',
        port='5432',
        database='lavadero_luissalgado'
    )

def agregar_cliente():
    try:
        conexion = conectar_db()
        cursor = conexion.cursor()
        sql = '''INSERT INTO clientes (nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono) 
                 VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)'''
        datos = (
            nombre_var.get(), apellido_var.get(), tipo_documento_var.get(), numero_documento_var.get(),
            fecha_nacimiento_var.get(), genero_var.get(), correo_var.get(), direccion_var.get(), telefono_var.get()
        )
        cursor.execute(sql, datos)
        conexion.commit()
        messagebox.showinfo('Éxito', 'Cliente agregado correctamente')
        listar_clientes()
    except Exception as e:
        messagebox.showerror('Error', f'No se pudo agregar el cliente: {e}')
    finally:
        cursor.close()
        conexion.close()

def listar_clientes():
    try:
        conexion = conectar_db()
        cursor = conexion.cursor()
        cursor.execute('SELECT * FROM clientes')
        registros = cursor.fetchall()
        for row in tabla.get_children():
            tabla.delete(row)
        for registro in registros:
            tabla.insert('', 'end', values=registro)
    except Exception as e:
        messagebox.showerror('Error', f'No se pudieron listar los clientes: {e}')
    finally:
        cursor.close()
        conexion.close()

def actualizar_cliente():
    try:
        conexion = conectar_db()
        cursor = conexion.cursor()
        sql = '''UPDATE clientes SET nombre = %s, apellido = %s, tipoDocumento = %s, numeroDocumento = %s, 
                 fechaNacimiento = %s, genero = %s, correoElectronico = %s, direccion = %s, telefono = %s 
                 WHERE clienteID = %s'''
        datos = (
            nombre_var.get(), apellido_var.get(), tipo_documento_var.get(), numero_documento_var.get(),
            fecha_nacimiento_var.get(), genero_var.get(), correo_var.get(), direccion_var.get(), telefono_var.get(),
            cliente_id_var.get()
        )
        cursor.execute(sql, datos)
        conexion.commit()
        messagebox.showinfo('Éxito', 'Cliente actualizado correctamente')
        listar_clientes()
    except Exception as e:
        messagebox.showerror('Error', f'No se pudo actualizar el cliente: {e}')
    finally:
        cursor.close()
        conexion.close()

def eliminar_cliente():
    try:
        conexion = conectar_db()
        cursor = conexion.cursor()
        sql = 'DELETE FROM clientes WHERE clienteID = %s'
        datos = (cliente_id_var.get(),)
        cursor.execute(sql, datos)
        conexion.commit()
        messagebox.showinfo('Éxito', 'Cliente eliminado correctamente')
        listar_clientes()
    except Exception as e:
        messagebox.showerror('Error', f'No se pudo eliminar el cliente: {e}')
    finally:
        cursor.close()
        conexion.close()

# Interfaz gráfica
root = tk.Tk()
root.title('Gestión de Clientes')
root.geometry('900x600')

# Variables
cliente_id_var = tk.StringVar()
nombre_var = tk.StringVar()
apellido_var = tk.StringVar()
tipo_documento_var = tk.StringVar()
numero_documento_var = tk.StringVar()
fecha_nacimiento_var = tk.StringVar()
genero_var = tk.StringVar()
correo_var = tk.StringVar()
direccion_var = tk.StringVar()
telefono_var = tk.StringVar()

# Campos de entrada
frame = tk.Frame(root)
frame.pack(pady=10)

tk.Label(frame, text='ID Cliente').grid(row=0, column=0)
tk.Entry(frame, textvariable=cliente_id_var).grid(row=0, column=1)

tk.Label(frame, text='Nombre').grid(row=1, column=0)
tk.Entry(frame, textvariable=nombre_var).grid(row=1, column=1)

tk.Label(frame, text='Apellido').grid(row=2, column=0)
tk.Entry(frame, textvariable=apellido_var).grid(row=2, column=1)

tk.Label(frame, text='Tipo Documento').grid(row=3, column=0)
tk.Entry(frame, textvariable=tipo_documento_var).grid(row=3, column=1)

tk.Label(frame, text='Número Documento').grid(row=4, column=0)
tk.Entry(frame, textvariable=numero_documento_var).grid(row=4, column=1)

tk.Label(frame, text='Fecha Nacimiento').grid(row=5, column=0)
tk.Entry(frame, textvariable=fecha_nacimiento_var).grid(row=5, column=1)

tk.Label(frame, text='Género').grid(row=6, column=0)
tk.Entry(frame, textvariable=genero_var).grid(row=6, column=1)

tk.Label(frame, text='Correo Electrónico').grid(row=7, column=0)
tk.Entry(frame, textvariable=correo_var).grid(row=7, column=1)

tk.Label(frame, text='Dirección').grid(row=8, column=0)
tk.Entry(frame, textvariable=direccion_var).grid(row=8, column=1)

tk.Label(frame, text='Teléfono').grid(row=9, column=0)
tk.Entry(frame, textvariable=telefono_var).grid(row=9, column=1)

# Botones
tk.Button(frame, text='Agregar', command=agregar_cliente).grid(row=10, column=0, pady=10)
tk.Button(frame, text='Actualizar', command=actualizar_cliente).grid(row=10, column=1)
tk.Button(frame, text='Eliminar', command=eliminar_cliente).grid(row=10, column=2)
tk.Button(frame, text='Listar', command=listar_clientes).grid(row=10, column=3)

# Tabla para mostrar clientes
tabla = ttk.Treeview(root, columns=("ID", "Nombre", "Apellido", "Tipo Doc", "Num Doc", "Fecha Nac", "Género", "Correo", "Dirección", "Teléfono"), show='headings')

for col in tabla['columns']:
    tabla.heading(col, text=col)
    tabla.column(col, width=100)

tabla.pack(fill=tk.BOTH, expand=True)

# Inicializar la lista de clientes
listar_clientes()

root.mainloop()
