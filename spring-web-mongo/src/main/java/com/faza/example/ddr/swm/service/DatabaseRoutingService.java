package com.faza.example.ddr.swm.service;

import jakarta.annotation.Nullable;
import java.util.function.Supplier;

public interface DatabaseRoutingService {

    <T> T execute(@Nullable String name, Supplier<T> supplier);
}
