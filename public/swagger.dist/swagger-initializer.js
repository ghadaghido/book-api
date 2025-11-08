
window.onload = function () {
    // Initialize Swagger UI when the page is loaded
    window.ui = SwaggerUIBundle({

        url: "/assets/swagger.yaml",


        dom_id: "#swagger-ui",

        deepLinking: true,


        presets: [
            SwaggerUIBundle.presets.apis,
            SwaggerUIStandalonePreset
        ],


        plugins: [
            SwaggerUIBundle.plugins.DownloadUrl
        ],

        layout: "StandaloneLayout"
    });
};