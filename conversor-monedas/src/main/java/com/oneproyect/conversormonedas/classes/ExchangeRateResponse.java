package com.oneproyect.conversormonedas.classes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Clase que representa la respuesta JSON recibida desde la API de tasas de cambio.
 * Contiene información general, tiempos de actualización y el mapa con las tasas.
 * 
 * Usa Lombok para generar getters, setters y constructores automáticamente.
 * 
 * @author Luciano Emmanuel Gatti Flekenstein
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

    /**
     * Resultado de la consulta a la API, por ejemplo: "success" o "error".
     */
    private String result;

    /**
     * URL con la documentación de la API.
     */
    private String documentation;

    /**
     * URL con los términos de uso.
     * Se mapea el campo JSON "terms_of_use" a la propiedad termsUse.
     */
    @SerializedName("terms_of_use")
    private String termsOfUse;

    /**
     * Marca de tiempo UNIX de la última actualización de las tasas.
     * Se mapea el campo JSON "time_last_update_unix".
     */
    @SerializedName("time_last_update_unix")
    private long timeLastUpdateUnix;

    /**
     * Fecha y hora UTC de la última actualización de las tasas.
     * Se mapea el campo JSON "time_last_update_utc".
     */
    @SerializedName("time_last_update_utc")
    private String timeLastUpdateUtc;

    /**
     * Marca de tiempo UNIX para la próxima actualización prevista.
     * Se mapea el campo JSON "time_next_update_unix".
     */
    @SerializedName("time_next_update_unix")
    private long timeNextUpdateUnix;

    /**
     * Fecha y hora UTC para la próxima actualización prevista.
     * Se mapea el campo JSON "time_next_update_utc".
     */
    @SerializedName("time_next_update_utc")
    private String timeNextUpdateUtc;

    /**
     * Código de la moneda base respecto a la cual se dan las tasas de conversión.
     * Se mapea el campo JSON "base_code".
     */
    @SerializedName("base_code")
    private String baseCode;

    /**
     * Mapa con las tasas de conversión. La clave es el código de la moneda destino,
     * y el valor es la tasa de cambio (BigDecimal para precisión).
     * Se mapea el campo JSON "conversion_rates".
     */
    @SerializedName("conversion_rates")
    private Map<String, BigDecimal> conversionRates;
}
