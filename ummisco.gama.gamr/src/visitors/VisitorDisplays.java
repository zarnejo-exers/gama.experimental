package visitors;

import java.util.Map;

import markdownSyntactic.IParser;
import markdownSyntactic.MarkdownTools;
import msi.gaml.compilation.ast.ISyntacticElement;
import msi.gaml.compilation.ast.ISyntacticElement.SyntacticVisitor;
/**
 * 
 * @author damienphilippon
 * Date : 19 Dec 2017
 * Class representing the visitor of ISyntacticElement representing Displays defined in GAML. This visitor
 * will generate the Markdown text for a visited ISyntacticElement for the Documentation
 */
public class VisitorDisplays implements SyntacticVisitor{
	/**
	 * Variable that will contain the markdown text generated by the visitor
	 */
	StringBuilder mDText;
	/**
	 * Variable representing all the species and the link to the documentation files that will present them
	 */
	Map<String, String> speciesLink;
	
	/**
	 * Variable representing all the experiments and the link to the documentation files that will present them
	 */
	Map<String, String> experimentsLink;
	
	/**
	 * Constructor of the visitor, using the linkSpecies and linkExperiments for replacing unknown variable types by their corresponding variables
	 * @param linkSpecies {@code Map<String, String>}, the map giving the link of a species to the markdown document describing it
	 * @param linkExperiments {@code Map<String, String>}, the map giving the link of an experiment to the markdown document describing it
	 */
	public VisitorDisplays(Map<String, String> linkSpecies,Map<String, String> linkExperiments)
	{
		this.speciesLink=linkSpecies;
		this.experimentsLink=linkExperiments;
	}
	/**
	 * Method to directly initialise the markdown text of the visitor, in order to let it add its generated text and return it to the model descriptor
	 * @param aBuilder {@code StringBuilder}, the StringBuilder of a model descriptor that will receive the generated text
	 */
	public void setText(StringBuilder aBuilder)
	{
		mDText=aBuilder;
	}
	
	/**
	 * Function that returns the StringBuilder of a Display Visitor once the visitor has done its job (adding text of a Display)
	 * @return {@code StringBuilder} the StringBuilder of a model descriptor 
	 */
	public StringBuilder getText()
	{
		return mDText;
	}
	
	/**
	 * Method to dispose all the objects that have been used by the VisitorDisplays and release memory
	 */
	public void dispose()
	{
		this.mDText=null;
		this.experimentsLink=null;
		this.speciesLink=null;
	}
	/**
	 * Method used to visit a ISyntacticElement (expecting a Display here), generating the markdown Text of it
	 * @param element {@code ISyntacticElement}, the ISyntacticElement representing a Display that will be used to generate the markdown code
	 */
	public void visit(ISyntacticElement element) {
		
		//If the current element is an output, we visit its children
		if(element.getKeyword().equals("output"))
		{
			element.visitAllChildren(this);
		}
		else
		{
			//If it is a display, we generate its text (display name) and visit its children
			if(element.getKeyword().equals("display"))
			{
				VisitorDebug.DEBUG("          doing the display "+element.getName());
				mDText.append(MarkdownTools.goBeginLine());
				mDText.append(IParser.MARKDOWN_KEYWORD_LIST+IParser.MARKDOWN_KEYWORD_SPACE+element.getKeyword()+IParser.MARKDOWN_KEYWORD_SPACE+element.getName());
				mDText.append(MarkdownTools.addQuote(MarkdownTools.getCommentsFromElement(element.getElement())));
				element.visitAllChildren(this);
			}
			else
			{
				//If it is a population (species), we generate its text (species name aspect name)
				if(element.getKeyword().equals("display_population"))
				{
					VisitorDebug.DEBUG("              doing the species "+element.getName());
					mDText.append(MarkdownTools.goBeginLine());
					mDText.append(IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_LIST+IParser.MARKDOWN_KEYWORD_SPACE+element.getExpressionAt("aspect")+" ("+MarkdownTools.addLink(element.getExpressionAt("species").toString(),speciesLink.get(element.getExpressionAt("species").toString()))+")");
					mDText.append(MarkdownTools.addQuote(MarkdownTools.getCommentsFromElement(element.getElement())));
				}
				else
				{
					//If it is a grid, we generate its text (grid name) 
					if(element.getKeyword().equals("display_grid"))
					{
						VisitorDebug.DEBUG("              doing the grid "+element.getName());
						mDText.append(MarkdownTools.goBeginLine());
						mDText.append(IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_LIST+IParser.MARKDOWN_KEYWORD_SPACE+MarkdownTools.addLink(element.getExpressionAt("species").toString(),speciesLink.get(element.getExpressionAt("species").toString())));
						mDText.append(MarkdownTools.addQuote(MarkdownTools.getCommentsFromElement(element.getElement())));
					}
					else
					{
						//If it is a chart, we generate its text (chart name type name) 
						if(element.getKeyword().equals("chart"))
						{
							VisitorDebug.DEBUG("              doing the chart "+element.getName());
							mDText.append(MarkdownTools.goBeginLine());
							mDText.append(IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_SPACE+IParser.MARKDOWN_KEYWORD_LIST+IParser.MARKDOWN_KEYWORD_SPACE+element.getKeyword()+IParser.MARKDOWN_KEYWORD_SPACE+element.getExpressionAt("type"));
							mDText.append(MarkdownTools.addQuote(MarkdownTools.getCommentsFromElement(element.getElement())));
						}
					}
				}
			}
		}
	}
}