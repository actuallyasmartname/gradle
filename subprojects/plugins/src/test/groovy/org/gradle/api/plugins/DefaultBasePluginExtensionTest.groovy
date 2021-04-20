/*
 * Copyright 2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.plugins

import org.gradle.api.plugins.internal.DefaultBasePluginConvention
import org.gradle.api.plugins.internal.DefaultBasePluginExtension
import org.gradle.test.fixtures.AbstractProjectBuilderSpec

class DefaultBasePluginExtensionTest extends AbstractProjectBuilderSpec {

    private BasePluginConvention convention
    private BasePluginExtension extension

    def setup() {
        extension = new DefaultBasePluginExtension(project)
        convention = new DefaultBasePluginConvention(extension)
    }

    def "default values"() {
        expect:
        extension.archivesBaseName == project.name
        extension.distsDirName == 'distributions'
        extension.distsDirectory.getAsFile().get() == project.layout.buildDirectory.dir('distributions').get().asFile
        extension.libsDirName == 'libs'
        extension.libsDirectory.getAsFile().get() == project.layout.buildDirectory.dir('libs').get().asFile
    }

    def "dirs relative to build dir"() {
        when:
        project.buildDir = project.file('mybuild')
        extension.distsDirName = 'mydists'
        extension.libsDirName = 'mylibs'

        then:
        convention.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')
        convention.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')
        extension.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')
    }

    def "dirs are cached properly"() {
        when:
        project.buildDir = project.file('mybuild')
        extension.distsDirName = 'mydists'

        then:
        convention.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')
        extension.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')

        when:
        extension.libsDirName = 'mylibs'

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')

        when:
        extension.distsDirName = 'mydists2'

        then:
        convention.distsDirectory.getAsFile().get() == project.file('mybuild/mydists2')
        extension.distsDirectory.getAsFile().get() == project.file('mybuild/mydists2')

        when:
        extension.libsDirName = 'mylibs2'

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs2')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs2')

        when:
        project.buildDir = project.file('mybuild2')

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild2/mylibs2')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild2/mylibs2')

        when:
        project.buildDir = project.file('mybuild')
        extension.distsDirName = 'mydists'

        then:
        convention.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')
        extension.distsDirectory.getAsFile().get() == project.file('mybuild/mydists')

        when:
        extension.libsDirName = 'mylibs'

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs')

        when:
        extension.distsDirName = 'mydists2'

        then:
        convention.distsDirectory.getAsFile().get() == project.file('mybuild/mydists2')
        extension.distsDirectory.getAsFile().get() == project.file('mybuild/mydists2')

        when:
        extension.libsDirName = 'mylibs2'

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs2')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild/mylibs2')

        when:
        project.buildDir = project.file('mybuild2')

        then:
        convention.libsDirectory.getAsFile().get() == project.file('mybuild2/mylibs2')
        extension.libsDirectory.getAsFile().get() == project.file('mybuild2/mylibs2')
    }
}
