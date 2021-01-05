package com.dollar.api.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductRequest {

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private int qte;

    @NotNull
    private Long category;
}
