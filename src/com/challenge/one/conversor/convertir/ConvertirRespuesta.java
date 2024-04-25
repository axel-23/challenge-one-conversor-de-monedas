package com.challenge.one.conversor.convertir;

import com.google.gson.annotations.SerializedName;

public record ConvertirRespuesta(String result, String base_code, String target_code, double conversion_rate, double conversion_result, @SerializedName("error-type") String error_type) {

    @Override
    public String toString() {
        double cantidad = conversion_result / conversion_rate;
        return  String.format("Resultado de la conversion de: %.4f [%s] a %.4f [%s]", cantidad, base_code, conversion_result, target_code);
    }
}