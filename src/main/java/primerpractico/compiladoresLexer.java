// Generated from c:/Users/Fede/Desktop/practicoTc/primerpractico/src/main/java/primerpractico/compiladores.g4 by ANTLR 4.13.1

package primerpractico;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class compiladoresLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NUMERO=1, INT=2, DOUBLE=3, VOID=4, IF=5, ELSE=6, FOR=7, WHILE=8, RETURN=9, 
		BOOL=10, PYC=11, COMA=12, IGUAL=13, SUMA=14, RESTA=15, MULT=16, DIV=17, 
		MOD=18, PA=19, PC=20, LLA=21, LLC=22, COMP=23, ID=24, PUNTO=25, WS=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DIGITO", "CARACTER", "NUMERO", "INT", "DOUBLE", "VOID", "IF", "ELSE", 
			"FOR", "WHILE", "RETURN", "BOOL", "PYC", "COMA", "IGUAL", "SUMA", "RESTA", 
			"MULT", "DIV", "MOD", "PA", "PC", "LLA", "LLC", "COMP", "ID", "PUNTO", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'int'", "'double'", "'void'", "'if'", "'else'", "'for'", 
			"'while'", "'return'", "'bool'", "';'", "','", "'='", "'+'", "'-'", "'*'", 
			"'/'", "'%'", "'('", "')'", "'{'", "'}'", null, null, "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NUMERO", "INT", "DOUBLE", "VOID", "IF", "ELSE", "FOR", "WHILE", 
			"RETURN", "BOOL", "PYC", "COMA", "IGUAL", "SUMA", "RESTA", "MULT", "DIV", 
			"MOD", "PA", "PC", "LLA", "LLC", "COMP", "ID", "PUNTO", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public compiladoresLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "compiladores.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001a\u00a8\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0004\u0002?\b\u0002\u000b\u0002\f\u0002@\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0092"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u0096\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0005\u0019\u009b\b\u0019\n\u0019\f\u0019\u009e"+
		"\t\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0004\u001b\u00a3\b\u001b"+
		"\u000b\u001b\f\u001b\u00a4\u0001\u001b\u0001\u001b\u0000\u0000\u001c\u0001"+
		"\u0000\u0003\u0000\u0005\u0001\u0007\u0002\t\u0003\u000b\u0004\r\u0005"+
		"\u000f\u0006\u0011\u0007\u0013\b\u0015\t\u0017\n\u0019\u000b\u001b\f\u001d"+
		"\r\u001f\u000e!\u000f#\u0010%\u0011\'\u0012)\u0013+\u0014-\u0015/\u0016"+
		"1\u00173\u00185\u00197\u001a\u0001\u0000\u0004\u0001\u000009\u0002\u0000"+
		"AZaz\u0002\u0000<<>>\u0003\u0000\t\n\r\r  \u00af\u0000\u0005\u0001\u0000"+
		"\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000"+
		"\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000"+
		"\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000"+
		"\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000"+
		"\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000"+
		"\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000"+
		"\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000"+
		"#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001"+
		"\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000"+
		"\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u0000"+
		"1\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001"+
		"\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00019\u0001\u0000\u0000"+
		"\u0000\u0003;\u0001\u0000\u0000\u0000\u0005>\u0001\u0000\u0000\u0000\u0007"+
		"B\u0001\u0000\u0000\u0000\tF\u0001\u0000\u0000\u0000\u000bM\u0001\u0000"+
		"\u0000\u0000\rR\u0001\u0000\u0000\u0000\u000fU\u0001\u0000\u0000\u0000"+
		"\u0011Z\u0001\u0000\u0000\u0000\u0013^\u0001\u0000\u0000\u0000\u0015d"+
		"\u0001\u0000\u0000\u0000\u0017k\u0001\u0000\u0000\u0000\u0019p\u0001\u0000"+
		"\u0000\u0000\u001br\u0001\u0000\u0000\u0000\u001dt\u0001\u0000\u0000\u0000"+
		"\u001fv\u0001\u0000\u0000\u0000!x\u0001\u0000\u0000\u0000#z\u0001\u0000"+
		"\u0000\u0000%|\u0001\u0000\u0000\u0000\'~\u0001\u0000\u0000\u0000)\u0080"+
		"\u0001\u0000\u0000\u0000+\u0082\u0001\u0000\u0000\u0000-\u0084\u0001\u0000"+
		"\u0000\u0000/\u0086\u0001\u0000\u0000\u00001\u0091\u0001\u0000\u0000\u0000"+
		"3\u0095\u0001\u0000\u0000\u00005\u009f\u0001\u0000\u0000\u00007\u00a2"+
		"\u0001\u0000\u0000\u00009:\u0007\u0000\u0000\u0000:\u0002\u0001\u0000"+
		"\u0000\u0000;<\u0007\u0001\u0000\u0000<\u0004\u0001\u0000\u0000\u0000"+
		"=?\u0003\u0001\u0000\u0000>=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000"+
		"\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000A\u0006\u0001"+
		"\u0000\u0000\u0000BC\u0005i\u0000\u0000CD\u0005n\u0000\u0000DE\u0005t"+
		"\u0000\u0000E\b\u0001\u0000\u0000\u0000FG\u0005d\u0000\u0000GH\u0005o"+
		"\u0000\u0000HI\u0005u\u0000\u0000IJ\u0005b\u0000\u0000JK\u0005l\u0000"+
		"\u0000KL\u0005e\u0000\u0000L\n\u0001\u0000\u0000\u0000MN\u0005v\u0000"+
		"\u0000NO\u0005o\u0000\u0000OP\u0005i\u0000\u0000PQ\u0005d\u0000\u0000"+
		"Q\f\u0001\u0000\u0000\u0000RS\u0005i\u0000\u0000ST\u0005f\u0000\u0000"+
		"T\u000e\u0001\u0000\u0000\u0000UV\u0005e\u0000\u0000VW\u0005l\u0000\u0000"+
		"WX\u0005s\u0000\u0000XY\u0005e\u0000\u0000Y\u0010\u0001\u0000\u0000\u0000"+
		"Z[\u0005f\u0000\u0000[\\\u0005o\u0000\u0000\\]\u0005r\u0000\u0000]\u0012"+
		"\u0001\u0000\u0000\u0000^_\u0005w\u0000\u0000_`\u0005h\u0000\u0000`a\u0005"+
		"i\u0000\u0000ab\u0005l\u0000\u0000bc\u0005e\u0000\u0000c\u0014\u0001\u0000"+
		"\u0000\u0000de\u0005r\u0000\u0000ef\u0005e\u0000\u0000fg\u0005t\u0000"+
		"\u0000gh\u0005u\u0000\u0000hi\u0005r\u0000\u0000ij\u0005n\u0000\u0000"+
		"j\u0016\u0001\u0000\u0000\u0000kl\u0005b\u0000\u0000lm\u0005o\u0000\u0000"+
		"mn\u0005o\u0000\u0000no\u0005l\u0000\u0000o\u0018\u0001\u0000\u0000\u0000"+
		"pq\u0005;\u0000\u0000q\u001a\u0001\u0000\u0000\u0000rs\u0005,\u0000\u0000"+
		"s\u001c\u0001\u0000\u0000\u0000tu\u0005=\u0000\u0000u\u001e\u0001\u0000"+
		"\u0000\u0000vw\u0005+\u0000\u0000w \u0001\u0000\u0000\u0000xy\u0005-\u0000"+
		"\u0000y\"\u0001\u0000\u0000\u0000z{\u0005*\u0000\u0000{$\u0001\u0000\u0000"+
		"\u0000|}\u0005/\u0000\u0000}&\u0001\u0000\u0000\u0000~\u007f\u0005%\u0000"+
		"\u0000\u007f(\u0001\u0000\u0000\u0000\u0080\u0081\u0005(\u0000\u0000\u0081"+
		"*\u0001\u0000\u0000\u0000\u0082\u0083\u0005)\u0000\u0000\u0083,\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0005{\u0000\u0000\u0085.\u0001\u0000\u0000"+
		"\u0000\u0086\u0087\u0005}\u0000\u0000\u00870\u0001\u0000\u0000\u0000\u0088"+
		"\u0089\u0005=\u0000\u0000\u0089\u0092\u0005=\u0000\u0000\u008a\u008b\u0005"+
		"!\u0000\u0000\u008b\u0092\u0005=\u0000\u0000\u008c\u0092\u0007\u0002\u0000"+
		"\u0000\u008d\u008e\u0005<\u0000\u0000\u008e\u0092\u0005=\u0000\u0000\u008f"+
		"\u0090\u0005>\u0000\u0000\u0090\u0092\u0005=\u0000\u0000\u0091\u0088\u0001"+
		"\u0000\u0000\u0000\u0091\u008a\u0001\u0000\u0000\u0000\u0091\u008c\u0001"+
		"\u0000\u0000\u0000\u0091\u008d\u0001\u0000\u0000\u0000\u0091\u008f\u0001"+
		"\u0000\u0000\u0000\u00922\u0001\u0000\u0000\u0000\u0093\u0096\u0003\u0003"+
		"\u0001\u0000\u0094\u0096\u0005_\u0000\u0000\u0095\u0093\u0001\u0000\u0000"+
		"\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0096\u009c\u0001\u0000\u0000"+
		"\u0000\u0097\u009b\u0003\u0003\u0001\u0000\u0098\u009b\u0003\u0001\u0000"+
		"\u0000\u0099\u009b\u0005_\u0000\u0000\u009a\u0097\u0001\u0000\u0000\u0000"+
		"\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000"+
		"\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000"+
		"\u009c\u009d\u0001\u0000\u0000\u0000\u009d4\u0001\u0000\u0000\u0000\u009e"+
		"\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0005.\u0000\u0000\u00a06\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a3\u0007\u0003\u0000\u0000\u00a2\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0006\u001b\u0000\u0000\u00a78\u0001\u0000"+
		"\u0000\u0000\u0007\u0000@\u0091\u0095\u009a\u009c\u00a4\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}