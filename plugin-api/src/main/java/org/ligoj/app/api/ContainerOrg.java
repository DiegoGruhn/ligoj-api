package org.ligoj.app.api;

import org.hibernate.validator.constraints.Length;
import org.ligoj.bootstrap.core.IDescribableBean;
import org.ligoj.bootstrap.core.NamedBean;

import lombok.Getter;
import lombok.Setter;

/**
 * A basic organizational container.<br>
 * "id" corresponds to the normalized name.<br>
 * "name" corresponds to the real name, not normalized.<br>
 * "description" corresponds to the normalized "Distinguished Name".
 */
@Getter
@Setter
public class ContainerOrg extends NamedBean<String> implements ResourceOrg, IDescribableBean<String> {

	/**
	 * Name pattern validation, includes LDAP injection protection.
	 */
	public static final String NAME_PATTERN = "[a-zA-Z0-9]([\\-: ]?[a-zA-Z0-9])+";

	/**
	 * Name pattern validation with string limits, includes LDAP injection protection.
	 */
	public static final String NAME_PATTERN_WRAPPER = "^" + NAME_PATTERN + "$";

	/**
	 * Flag indicating this container cannot be deleted or updated.
	 */
	private boolean locked;

	@Length(max = 512)
	private String description;

	/**
	 * All arguments constructor.
	 * 
	 * @param dn
	 *            Corresponds to the "Distinguished Name". Will be saved in "description".
	 * @param name
	 *            "name" corresponds to the display name. Will be saved in "name", and in "id" in is normalized form.
	 */
	public ContainerOrg(final String dn, final String name) {
		setId(Normalizer.normalize(name));
		setName(name);
		setDescription(dn);
	}

	@Override
	public String getDn() {
		return getDescription();
	}

	/**
	 * Return DN of given container or <code>null</code>.
	 * 
	 * @param container
	 *            The optional container.
	 * @return DN of given container or <code>null</code>.
	 */
	public static String getSafeDn(final ContainerOrg container) {
		return container == null ? null : container.getDn();
	}

}