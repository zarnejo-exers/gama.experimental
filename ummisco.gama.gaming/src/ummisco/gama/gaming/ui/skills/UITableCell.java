package ummisco.gama.gaming.ui.skills;

import msi.gama.precompiler.IConcept;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.skill;
import msi.gama.precompiler.GamlAnnotations.variable;
import msi.gama.precompiler.GamlAnnotations.vars;
import msi.gaml.types.IType;

@vars({ @variable(name = IUILocatedSkill.AGENT_LOCATION, type = IType.POINT, doc = @doc("locked location")),
	@variable(name = IUILocatedSkill.AGENT_LOCKED_WIDTH, type = IType.FLOAT, doc = @doc("locked width")),
	@variable(name = IUILocatedSkill.AGENT_LOCKED_HEIGHT, type = IType.FLOAT, doc = @doc("locked height")),
	@variable(name = IUILocatedSkill.AGENT_UI_WIDTH, type = IType.FLOAT, doc = @doc("resized width")),
	@variable(name = IUILocatedSkill.AGENT_UI_HEIGHT, type = IType.FLOAT, doc = @doc("resized height")),
	@variable(name = IUILocatedSkill.AGENT_DISPLAY, type = IType.STRING, doc = @doc("map of location"))})

@skill(name=IUITableCell.SKILL_NAME, concept = { IConcept.GUI, IConcept.COMMUNICATION, IConcept.SKILL })
public class UITableCell extends UILocatedSkill {
	
	

}
