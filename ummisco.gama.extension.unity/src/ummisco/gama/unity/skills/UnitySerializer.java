package ummisco.gama.unity.skills;

import org.locationtech.jts.geom.ShapeType;

import com.thoughtworks.xstream.XStream;

import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.util.GamaColor;

public class UnitySerializer {

	public static XStream xstream;

	public UnitySerializer() {

	}

	public UnitySerializer(final XStream xs) {
		this.xstream = xs;
	}

	public void SetSerializer(final XStream xs) {
		this.xstream = xs;
	}

	public String toXML(final Object message) {
		return xstream.toXML(message);
	}

	public String agentShapeToXML(final Object message) {

		// xstream.omitField(PropertyTopicMessage.class, "value"); // to Omit a specific field
		// xstream.aliasType("xsi:type=\"GamaShape\"", GamaShape.class);
		// xstream.aliasType("xsi:type", Object.class);
		// xstream.useAttributeFor("sender", type);

		/*
		 * xstream.alias("GamaMessage", GamaMessage.class); xstream.alias("GamaShape", GamaShape.class);
		 * xstream.alias("geometry", Polygon.class); xstream.alias("factory", GamaGeometryFactory.class);
		 * xstream.alias("GamaCoordinateSequence", GamaCoordinateSequence.class);
		 * xstream.alias("GamaCoordinateSequenceFactory", GamaCoordinateSequenceFactory.class);
		 *
		 * xstream.alias("MinimalAgent", MinimalAgent.class); xstream.alias("GamaStringType", GamaStringType.class);
		 * xstream.alias("GamaNoType", GamaNoType.class); xstream.alias("GamaColor", GamaColor.class);
		 * xstream.alias("GamaList", GamaList.class); xstream.alias("ParametricType", ParametricType.class);
		 * xstream.alias("GamaListType", GamaListType.class); xstream.alias("GamaMessageType", GamaMessageType.class);
		 * xstream.alias("GamaIntegerType", GamaIntegerType.class); xstream.alias("GamaMailbox", GamaMailbox.class);
		 *
		 * xstream.omitField(GamaGeometryFactory.class, "precisionModel"); //
		 * xstream.omitField(GamaGeometryFactory.class, "factory"); // xstream.omitField(GamaGeometryFactory.class,
		 * "factory"); //
		 */

		xstream.alias("geometryType", ShapeType.class);
		xstream.alias("color", GamaColor.class);
		xstream.alias("NamedGamaColor", msi.gama.util.GamaColor.NamedGamaColor.class);
		xstream.alias("GamaPoint", GamaPoint.class);
		xstream.alias("UnityAgent", UnityAgent.class);
		// xstream.alias("GamaMessage", GamaMessage.class);

		return xstream.toXML(message);
	}

}
