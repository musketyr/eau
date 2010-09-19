package eu.ebdit.eau;

import java.io.File;
import java.io.Serializable;
import java.util.Map;




/**
 * This class can be used to bear various values to test cases.
 * @author Vladimir Orany
 * @since 0.0.2
 */
public final class Porter{
	
	public static Map<String, Object> porters = new HashMap<String, Object>();
	
	/**
	 * Leases the Porter for the current JVM. 
	 * The porter will hold all given burdens unless the JVM which
	 * first called this method with specified name terminates. 
	 * Burdens are available to any other JVM.
	 * @param name identification of the Porter
	 * @return Porter for the given name
	 */
	public static Porter leasePorter(String name){
		if (porters[name]) {
			return porters[name]
		}
		Porter p = new Porter(name)
		porters[name] = p
		return p;
	}
	
	private final File propsFile;
	
	private Porter(String id){
		String fileName = Porter.class.getName() + "." + id + ".eau"
		propsFile = new File(System.getProperty("java.io.tmpdir") + File.separator + fileName);
		if (!propsFile.exists()) {
			propsFile.createNewFile();
			propsFile.deleteOnExit();
		}
	}
	
	
	
	/**
	 * Inspects the burden without removing it from the Porter.
	 * @param key identification of the burden
	 * @return the burden held by the Porter
	 */
	public Serializable inspectBurden(final String key){
		readMap()[key]
	}
	
	/**
	 * Gives the Porter the burden.
	 * The burden will be available to clients unless be taken away using {@link #takeBurden(String)}
	 * or the JVM where was at first called the Porter with specified name terminates.
	 * @param key identification of the burden
	 * @param burden the burden to be taken by the Porter
	 */
	public void giveBurden(final String key, final Serializable burden){
		Map<String, Object> theMap = readMap()
		theMap[key] = burden
		writeMap(theMap)
	}
	
	/**
	 * Takes the burden from the Porter so its no longer available to other clients.
	 * @param key identification of the burden
	 * @return the burden
	 */
	public Serializable takeBurden(final String key){
		Map<String, Object> theMap = readMap()
		Serializable burden = theMap.remove(key)
		writeMap(theMap)
		return burden
	}
	
	private Map<String, Object> readMap(){
		Map<String, Object> theMap = new HashMap<String, Object>()
		if (!propsFile.length()){
			return theMap
		}
		propsFile.withObjectInputStream { instream ->
			instream.eachObject {
				theMap = it
			}
		}
		return theMap
	}
	
	private void writeMap(Map<String, Object> theMap){
		propsFile.withObjectOutputStream { out ->
			out << theMap
		}
	}

}
