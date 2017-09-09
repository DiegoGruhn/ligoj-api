package org.ligoj.app.resource.node;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ligoj.app.model.NodeId;
import org.ligoj.app.model.Refining;
import org.ligoj.bootstrap.core.INamableBean;

import lombok.Getter;
import lombok.Setter;

/**
 * Node object for edition.
 */
@Getter
@Setter
public class NodeEditionVo extends AbstractParameteredVo implements INamableBean<String>, Refining<String> {

	@NotBlank
	@NotNull
	private String name;
	
	/**
	 * When <code>true</code> the previous parameters are not updated.
	 */
	private boolean untouchedParameters;

	/**
	 * The node identifier.
	 */
	@NotBlank
	@NotNull
	@NodeId
	String id;

	@Override
	public String getRefined() {
		return getNode();
	}

}
