
import { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [ciudadOrigen, setCiudadOrigen] = useState('');
  const [ciudadDestino, setCiudadDestino] = useState('');
  const [ciudades, setCiudades] = useState([]);
  const [resultado, setResultado] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchCiudades = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/cities');
        const data = await response.json();
        setCiudades(data);
      } catch (error) {
        console.error('Error al obtener ciudades:', error);
      }
    };
    fetchCiudades();
  }, []);

  const handleBuscar = async () => {
    if (ciudadOrigen && ciudadDestino && ciudadOrigen !== ciudadDestino) {
      try {
        const response = await fetch(`http://localhost:8080/api/find/${ciudadOrigen}/${ciudadDestino}`);
        if (response.ok) {
          const data = await response.json();
          setResultado(data);
          setError(false);
        } else {
          setError(true);
          setResultado(null);
        }
      } catch (error) {
        setError(true);
        setResultado(null);
      }
    } else {
      setError(true);
    }
  };

  const handleCerrarModal = () => {
    setError(false);
  };

  return (
    <div className="app-container">
      <div className="overlay">
        <h1>✈️ AV Viaje Por Colombia</h1>
        <div className="card">
          <select
            value={ciudadOrigen}
            onChange={(e) => setCiudadOrigen(e.target.value)}
          >
            <option value="">🛫 Selecciona Ciudad Origen</option>
            {ciudades.map((ciudad) => (
              <option key={ciudad} value={ciudad} disabled={ciudad === ciudadDestino}>
                {ciudad}
              </option>
            ))}
          </select>

          <select
            value={ciudadDestino}
            onChange={(e) => setCiudadDestino(e.target.value)}
          >
            <option value="">🛬 Selecciona Ciudad Destino</option>
            {ciudades.map((ciudad) => (
              <option key={ciudad} value={ciudad} disabled={ciudad === ciudadOrigen}>
                {ciudad}
              </option>
            ))}
          </select>

          <button onClick={handleBuscar}>🔍 Buscar Vuelos</button>
        </div>

        {error && (
          <div className="modal">
            <button className="close" onClick={handleCerrarModal}>✕</button>
            <h2>⚠️ Error</h2>
            <p>No se pudo encontrar una ruta válida entre las ciudades seleccionadas.</p>
          </div>
        )}

        {resultado && (
          <div className="resultado">
            <h2>🎉 ¡Vuelo Encontrado!</h2>
            <h3>{`${resultado.departurestation} ✈️ ${resultado.arrivalStation}`}</h3>
            <p className="precio-total">Precio Total: ${resultado.price}</p>

            <div className="flights-container">
              {resultado.flights.map((flight, index) => (
                <div key={flight.id} className="flight-card">
                  <h3>Vuelo {index + 1}</h3>
                  <p>✈️ {flight.transport.flightCarrier} - {flight.transport.flightNumber}</p>
                  <p>🛫 Origen: {flight.departureStation}</p>
                  <p>🛬 Destino: {flight.arrivalStation}</p>
                  <p>💰 Precio: ${flight.price}</p>
                </div>
              ))}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;