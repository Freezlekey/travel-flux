async function cargarHoteles() {
    const contenedor = document.getElementById("hoteles-container");

    try {
        const respuesta = await fetch("/api/hoteles/novedades");

        if (!respuesta.ok) {
            throw new Error("Error al cargar los hoteles");
        }

        const hoteles = await respuesta.json();

        contenedor.innerHTML = "";

        hoteles.forEach(hotel => {
            const tarjeta = document.createElement("article");
            tarjeta.className = "col-12 col-md-6 col-lg-3";

            const enlaceImagen = document.createElement("a");
            enlaceImagen.href = `detail.html?id=${hotel.id}&tipo=hotel`;

            const imagen = document.createElement("img");
            imagen.src = hotel.imagenUrl;
            imagen.alt = hotel.nombre;
            imagen.className = "img-fluid card-image";

            enlaceImagen.appendChild(imagen);

            const titulo = document.createElement("h3");
            titulo.className = "card-title";

            const enlaceTitulo = document.createElement("a");
            enlaceTitulo.href = `detail.html?id=${hotel.id}&tipo=hotel`;
            enlaceTitulo.textContent = hotel.nombre;

            titulo.appendChild(enlaceTitulo);

            tarjeta.appendChild(enlaceImagen);
            tarjeta.appendChild(titulo);

            contenedor.appendChild(tarjeta);
        });

    } catch (error) {
        console.error("Error:", error);
        contenedor.innerHTML = "<p>No se pudieron cargar los hoteles.</p>";
    }
}

document.addEventListener("DOMContentLoaded", cargarHoteles);