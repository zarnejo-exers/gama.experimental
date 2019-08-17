package ummisco.gama.unity.skills;

import java.util.ArrayList;

import msi.gama.metamodel.agent.MinimalAgent;
import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.util.GamaColor;
import msi.gama.util.GamaPair;
import msi.gama.util.IList;
import msi.gama.util.IMap.IPairList;

public class UnityAgent {

	public String agentName;
	public String species;
	public Object geometryType;
	public Object vertices;
	public GamaPoint location;

	public Object color;
	public Object height;

	public UnityAgent() {

	}

	public void getUnityAgent(final MinimalAgent miniAgent) {
		this.agentName = miniAgent.getName();
		this.species = miniAgent.getSpecies().getName();
		this.geometryType = miniAgent.getGeometricalType();
		this.vertices = getPointsList(miniAgent.getGeometry().getPoints());
		this.location = miniAgent.getLocation();

		// GamaShape gs = (GamaShape) miniAgent.getGeometry();
		// GamaPairList pairs = gs.getAttributes().getPairs();

		final IPairList pairs = miniAgent.getOrCreateAttributes().getPairs();

		System.out.println("Agent name is : " + this.agentName);

		for (final Object e : pairs) {
			final GamaPair gp = (GamaPair) e;

			switch ((String) gp.getKey()) {
				case "color":
					this.color = gp.getValue();
					break;
				case "height":
					this.height = gp.getValue();
					break;

				default:
					break;
			}

			// GamaColor color = gp.getKey().
			System.out.println("Object type is : " + e.getClass());
			System.out.println("Object key is : " + gp.getKey());
			System.out.println("Object value is : " + gp.getValue());

		}

	}

	public ArrayList<GamaPoint> getPointsList(final IList<? extends GamaPoint> list) {
		final ArrayList<GamaPoint> pointsList = new ArrayList<>();
		for (final GamaPoint l : list) {
			final GamaPoint p = new GamaPoint(l.getX(), l.getY(), l.getZ());
			pointsList.add(p);
		}
		return pointsList;
	}

	public GamaColor getGamaColor() {

		final GamaColor red = new GamaColor(217, 72, 33);

		return red;
	}
}
