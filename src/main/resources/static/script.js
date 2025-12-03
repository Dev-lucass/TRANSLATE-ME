document.addEventListener("DOMContentLoaded", () => {
    const form = document.querySelector(".send-box");
    const loader = document.getElementById("loadingOverlay");

    form.addEventListener("submit", (event) => {
        // Mostrar loader imediatamente
        loader.style.display = "flex";

        // Não precisamos impedir o submit, o form será enviado normalmente
        // apenas mostrar o loader antes de enviar
    });
});
