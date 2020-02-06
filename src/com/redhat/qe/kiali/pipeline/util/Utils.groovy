package com.redhat.qe.kiali.pipeline.util

class Utils {
    static def getKialiTag(script) {
        def docker_tag_kiali = ""
        if( script.params.CORE_PR != "") {
            docker_tag_kiali = docker_tag_kiali + "core" + script.params.CORE_PR
        }
        if( script.params.UI_PR != "") {
            docker_tag_kiali = docker_tag_kiali + "ui" + script.params.UI_PR
        }
        if( script.params.CORE_BRANCH != "master" && script.params.CORE_BRANCH != "") {
            docker_tag_kiali = docker_tag_kiali + "core" + script.params.CORE_BRANCH
        }
        if( script.params.UI_BRANCH != "master" && script.params.UI_BRANCH != "") {
            docker_tag_kiali = docker_tag_kiali + "ui" + script.params.UI_BRANCH
        }
        if(docker_tag_kiali == "") {
            docker_tag_kiali = "latest"
        }
        return docker_tag_kiali
    }
    static def getTest(script) {
        return "test"
    }
}
