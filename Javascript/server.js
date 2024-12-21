const express = require('express');
const { Pool } = require('pg');
const bodyParser = require('body-parser');

// Configuración de Express
const app = express();
app.use(bodyParser.json());

// Configuración de conexión a PostgreSQL
const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: 'lavadero_luissalgado',
  password: 'C.l.f3012',
  port: 5432,
});

// Ruta para insertar un cliente
app.post('/clientes', async (req, res) => {
  const { nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono } = req.body;

  const query = `
    INSERT INTO clientes (nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono)
    VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)
    RETURNING id;
  `;
  
  try {
    const result = await pool.query(query, [nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono]);
    res.status(201).json({ id: result.rows[0].id, message: 'Cliente registrado' });
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al registrar el cliente' });
  }
});

// Ruta para obtener todos los clientes
app.get('/clientes', async (req, res) => {
  try {
    const result = await pool.query('SELECT * FROM clientes');
    res.status(200).json(result.rows);
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al obtener los clientes' });
  }
});

// Ruta para obtener un cliente por ID
app.get('/clientes/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const result = await pool.query('SELECT * FROM clientes WHERE id = $1', [id]);
    if (result.rows.length > 0) {
      res.status(200).json(result.rows[0]);
    } else {
      res.status(404).json({ error: 'Cliente no encontrado' });
    }
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al obtener el cliente' });
  }
});

// Ruta para actualizar un cliente
app.put('/clientes/:id', async (req, res) => {
  const { id } = req.params;
  const { nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono } = req.body;

  const query = `
    UPDATE clientes
    SET nombre = $1, apellido = $2, tipoDocumento = $3, numeroDocumento = $4, fechaNacimiento = $5, genero = $6, correoElectronico = $7, direccion = $8, telefono = $9
    WHERE id = $10
    RETURNING id;
  `;

  try {
    const result = await pool.query(query, [nombre, apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, correoElectronico, direccion, telefono, id]);
    if (result.rows.length > 0) {
      res.status(200).json({ id: result.rows[0].id, message: 'Cliente actualizado' });
    } else {
      res.status(404).json({ error: 'Cliente no encontrado' });
    }
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al actualizar el cliente' });
  }
});

// Ruta para eliminar un cliente
app.delete('/clientes/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const result = await pool.query('DELETE FROM clientes WHERE id = $1', [id]);
    if (result.rowCount > 0) {
      res.status(200).json({ message: 'Cliente eliminado' });
    } else {
      res.status(404).json({ error: 'Cliente no encontrado' });
    }
  } catch (err) {
    console.error(err);
    res.status(500).json({ error: 'Error al eliminar el cliente' });
  }
});

// Iniciar el servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en el puerto ${PORT}`);
});
