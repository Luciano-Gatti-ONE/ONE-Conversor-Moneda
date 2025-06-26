# 🏦 Conversor de Monedas

Conversor de Monedas es una aplicación de consola en Java que permite convertir montos entre USD, ARS, BRL y COP utilizando datos actualizados desde una API externa. Ideal para aprender buenas prácticas, flujo de datos y manejo preciso de valores financieros.

---

## 🚀 Descripción

- ✅ Permite elegir entre 12 combinaciones posibles de conversión.
- 🔄 Obtiene tasas de cambio una sola vez al iniciar (moneda base: USD).
- 💰 Utiliza `BigDecimal` para cálculos de alta precisión.
- 💻 Usa `HttpClient` para solicitudes HTTP y `Gson` para parsear JSON.
- 📋 Menú interactivo con pausa para que el usuario pueda leer los resultados con tranquilidad.

---

## 🛠️ Características

- **Modular**: separación clara entre presentación (CLI), lógica de negocio y modelo de datos.
- **Confiable**: control de errores de entrada y tasas no disponibles.
- **Escalable**: base ideal para agregar nuevas monedas o mejorar la interfaz.
- **Profesional**: código documentado con comentarios y JavaDoc.

---

## ⚙️ Instalación y Uso

```bash
git clone https://github.com/<tu-usuario>/conversor-de-monedas.git
cd conversor-de-monedas
mvn clean compile
```

Para ejecutar:

```bash
mvn exec:java -Dexec.mainClass="com.oneproyect.conversormonedas.ConversorMonedas"
```

Sigue los pasos del menú para convertir entre monedas. ¡Y listo!

---

## 🧩 Estructura del Proyecto

```
src/
 └─ main/java/com/oneproyect/conversormonedas/
     ├── ConversorMonedas.java             # Clase principal (Main)
     ├── service/
     │   └── CurrencyService.java          # Lógica de negocio
     └── classes/
         └── ExchangeRateResponse.java     # DTO para respuesta de la API
```

---

## 📚 Tecnologías usadas

- **Java 21** – `HttpClient`
- **Gson** – para parseo JSON (`@SerializedName`)
- **Lombok** – para reducir código repetitivo (getters, setters, constructores)
- **Maven** – para gestión de dependencias y compilación

---

## 🌐 Fuente de Datos

Esta aplicación utiliza [ExchangeRate-API](https://www.exchangerate-api.com/), un servicio gratuito y confiable para obtener tasas de cambio actualizadas entre monedas.

- Documentación: [https://www.exchangerate-api.com/docs](https://www.exchangerate-api.com/docs)

---

## ✍️ Autor

**Luciano Emmanuel Gatti Flekenstein**  
Aplicación creada como proyecto educativo y para mostrar buenas prácticas de desarrollo en Java, manejo de APIs y procesamiento numérico preciso.

---

## 📄 Licencia

Proyecto de código abierto. ¡Sos libre de usarlo y adaptarlo! 😀
