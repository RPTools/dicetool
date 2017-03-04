function registerFunctions() {
	var map = new java.util.HashMap();

	map.put("label", label);
	map.put("l", label);
	
	map.put("rr", rrLabel);

	map.put("manyAttack", manyAttack);
	map.put("ma", manyAttack);
	return map;
}

function label(a, lbl) {
	row.setLabel(lbl);
	return a;
}

function rrLabel(a, lbl) {
	row.setReRollExpression(lbl);
	return a;
}

function manyAttack(number, ab, critRange, ac) {
	var hits = 0;
	var crits = 0;

	for (var i = 0; i < number; i++) {
		var roll = rand.nextInt(20) + 1;
		var critRoll = rand.nextInt(20) + 1;
		if (roll == 20 || roll + ab >= ac) {
			hits++;
			
			if (roll >= critRange && (critRoll == 20 || critRoll + ab >= ac)) {
				crits++;
			}
		}
	}

	if (crits > 0) {
		resultSet.addExpression("crits", String.valueOf(crits), null, crits);
	}
	
	return hits;
}
