package com.faza.example.ddr.swj.service;

import jakarta.annotation.Nullable;
import java.util.function.Supplier;

public interface DatabaseRoutingService {

    <T> T execute(@Nullable String name, Supplier<T> supplier);
}
