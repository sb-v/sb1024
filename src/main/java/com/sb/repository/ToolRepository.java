package com.sb.repository;

import com.sb.domain.Charge;
import com.sb.domain.Tool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class loads the static data provided, there are many ways to load this data, I chose in memory for easy setup
 */
public class ToolRepository {

    private List<Tool> tools = new ArrayList<>();
    public ToolRepository() {
        tools.add(new Tool("LADW", "Ladder", "Werner",
                new Charge(new BigDecimal("1.99"), true, true, false)));
        tools.add(new Tool("CHNS", "Chainsaw", "Stihl",
                new Charge(new BigDecimal("1.49"), true, false, true)));
        tools.add(new Tool("JAKR", "Jackhammer", "Ridgid",
                new Charge(new BigDecimal("2.99"), true, false, false)));
        tools.add(new Tool("JAKD", "Jackhammer", "DeWalt",
                new Charge(new BigDecimal("2.99"), true, false, false)));
    }

    // Retrieve a tool by its tool code
    public Tool getToolByCode(String toolCode) {
        return tools.stream()
                .filter(tool -> tool.getCode().equalsIgnoreCase(toolCode))
                .findFirst()
                .orElse(null);
    }

}
