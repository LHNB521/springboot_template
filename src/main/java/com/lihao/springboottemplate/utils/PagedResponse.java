package com.lihao.springboottemplate.utils;

import java.util.List;

public record PagedResponse<T>(List<T> rows, long total) {
}