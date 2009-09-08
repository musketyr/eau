package eu.ebdit.eau.testing.annotations;

/**
 * Meaningful description of the test.
 * For example "Working addition" for test checking whether calculator adds appropriately.
 * @author Vladimir Orany
 */
public @interface Description {
	/** @returns the description */ String value();
}
