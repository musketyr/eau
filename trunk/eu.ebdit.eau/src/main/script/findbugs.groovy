import groovy.xml.MarkupBuilder

File outFile = new File("${project.build.directory}${File.separator}findbugs-exclude.xml")
println "Generating file $outFile"
if(!outFile.exists()) { outFile.createNewFile()}
outFile.withWriter { writer -> 
    builder = new MarkupBuilder(writer)
    builder.FindBugsFilter {
        Match {
            Or {
        	pom.basedir.eachFileRecurse{
                    if (it =~ /\.groovy$/) {
                          def simpleName = it.name.replace('.groovy','')
                          builder.Class(name: "~.*$simpleName\$")
                          builder.Class(name: "~.*$simpleName\\\$.*")
                    }
                }
            }
        }
    }	
}
println "File $outFile generated successfully"
