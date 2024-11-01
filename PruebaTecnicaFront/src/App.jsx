import { useState } from 'react'
import './App.css'

function App() {
  const [ciudadOrigen, setCiudadOrigen] = useState('')
  const [ciudadDestino, setCiudadDestino] = useState('')
  const [resultado, setResultado] = useState(null)
  const [error, setError] = useState(false)

  const handleBuscar = () => {
    if (ciudadOrigen && ciudadDestino) {
      // Simulación de resultado de búsqueda
      const busquedaExitosa = Math.random() > 0.5 // Cambiar esta línea para consulta real
      if (busquedaExitosa) {
        setResultado({
          titulo: `Vuelo ${ciudadOrigen} - ${ciudadDestino}`,
          precio: '0',
          compania: 'AV'
        })
        setError(false)
      } else {
        setError(true)
        setResultado(null)
      }
    }
  }

  const handleCerrarModal = () => {
    setError(false)
  }

  return (
    <>
      <h1>AV Viaje Por Colombia</h1>
      <div className="card">
        <input
          type="text"
          placeholder="Ciudad Origen"
          value={ciudadOrigen}
          onChange={(e) => setCiudadOrigen(e.target.value)}
        />
        <input
          type="text"
          placeholder="Ciudad Destino"
          value={ciudadDestino}
          onChange={(e) => setCiudadDestino(e.target.value)}
        />
        <button onClick={handleBuscar}>Buscar</button>
      </div>

      {error && (
        <div className="modal">
          <button className="close" onClick={handleCerrarModal}>X</button>
          <h2>Error</h2>
          <p>No se pudo encontrar una ruta.</p>
        </div>
      )}

      {resultado && (
        <div className="resultado">
          <h2>{resultado.titulo}</h2>
          <p>Precio: {resultado.precio}</p>
          <p>Compañía: {resultado.compania}</p>
        </div>
      )}
    </>
  )
}

export default App
