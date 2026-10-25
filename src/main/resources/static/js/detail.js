document.addEventListener("DOMContentLoaded", cargarDetalleHotel);

async function cargarDetalleHotel() {
    const contenedor = document.getElementById("detail-container");

    const parametros = new URLSearchParams(window.location.search);
    const id = parametros.get("id");
    const tipo = parametros.get("tipo");

    if (!id || tipo !== "hotel") {
        contenedor.innerHTML = `
            <p class="text-center">
                No se ha especificado un hotel válido.
            </p>
        `;
        return;
    }

    try {
        // Запрашиваем конкретный отель по его ID.
        const respuesta = await fetch(`/api/hoteles/${encodeURIComponent(id)}`);

        if (!respuesta.ok) {
            throw new Error("No se pudo cargar el hotel.");
        }

        const hotel = await respuesta.json();

        // Заголовок страницы.
        document.title = `TravelFlux - ${hotel.nombre}`;

        contenedor.innerHTML = `
            <header class="hotel-detail-header">
                <div class="container-fluid">
                    <div class="hotel-navigation">

                        <h2>${escaparHTML(hotel.nombre)}</h2>

                        <p class="detail-subtitle">
                            Hotel de ${hotel.estrellas} estrellas ·
                            ${escaparHTML(hotel.ubicacion)}
                        </p>

                        <div
                            class="hotel-rating"
                            aria-label="Hotel de ${hotel.estrellas} estrellas"
                        >
                            ${"★".repeat(Math.max(
            0,
            Math.min(5, Number(hotel.estrellas) || 0)
        ))}
                        </div>

                        <hr>

                        <a
                            href="index.html#hoteles"
                            class="detail-button"
                        >
                            Ver más hoteles
                        </a>

                    </div>
                </div>
            </header>
        `;

        // Заполняем информацию об отеле.
        document.getElementById("hotel-name").textContent =
            hotel.nombre || "";

        document.getElementById("hotel-description").textContent =
            hotel.descripcion || "Información no disponible.";

        document.getElementById("hotel-history").textContent =
            hotel.historia || "Historia no disponible.";

        document.getElementById("hotel-stars").textContent =
            `${hotel.estrellas} estrellas`;

        document.getElementById("hotel-location").textContent =
            hotel.ubicacion || "No disponible";

        document.getElementById("hotel-popularity").textContent =
            hotel.popularidad || "Información no disponible.";

        // Повторяем ключевые сведения в разделе Información útil.
        document.getElementById("info-stars").textContent =
            `${hotel.estrellas} estrellas`;

        document.getElementById("info-location").textContent =
            hotel.ubicacion || "No disponible";

        document.getElementById("info-name").textContent =
            hotel.nombre || "";

        // Фотография отеля.
        const imagen = document.getElementById("hotel-image");

        imagen.src = hotel.imagenUrl || "";
        imagen.alt = hotel.nombre || "Hotel";

        imagen.onerror = function () {
            this.hidden = true;
        };

        // Кнопка официального сайта.
        const enlace = document.getElementById("hotel-website");

        if (hotel.urlOficial && hotel.urlOficial.trim() !== "") {
            enlace.href = hotel.urlOficial;
            enlace.hidden = false;
        } else {
            enlace.hidden = true;
        }

    } catch (error) {
        console.error("Error al cargar el hotel:", error);

        contenedor.innerHTML = `
            <p class="text-center">
                No se pudo cargar la información del hotel.
                Comprueba que el servidor esté funcionando.
            </p>
        `;
    }
}

function escaparHTML(valor) {
    return String(valor ?? "").replace(/[&<>"']/g, caracter => {
        const entidades = {
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            '"': "&quot;",
            "'": "&#39;"
        };

        return entidades[caracter];
    });
}