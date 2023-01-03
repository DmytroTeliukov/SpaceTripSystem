package org.dteliukov.model.representation;

import org.dteliukov.model.domain.User;

public record OperatorData(User operator, String date, Integer countProcessedOrder) {
}
