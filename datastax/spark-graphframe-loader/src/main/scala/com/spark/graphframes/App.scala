package com.spark.graphframes

import com.datastax.bdp.graph.spark.graphframe._
import org.apache.spark.sql.SparkSession

object App {
  def main(args: Array[String]):Unit = {

    if (args.length < 1) {
      println("Please specify the following command-line arguments: name of the target graph and dsefs directory name")
    }
    val graphName = args(0)
    val dsefsDir = args(1)

    val spark = SparkSession
      .builder
      .appName("Graph Load Application")
      .enableHiveSupport()
      .getOrCreate()

    val inputPath = "dsefs:///" + dsefsDir + "/"
    println("input path that is being used: " + inputPath)

    val g = spark.dseGraph(graphName)


    /* CSV files containing vertices
     *  - comment_0_0.csv
     *  - forum_0_0.csv
     *  - person_0_0.csv
     *  - post_0_0.csv
     *  - organisation_0_0.csv
     *  - place_0_0.csv
     *  - tag_0_0.csv
     *  - tagclass_0_0.csv
     */

    g.updateVertices("comment",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "comment_0_0.csv"))
    g.updateVertices("forum",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "forum_0_0.csv"))
    g.updateVertices("person",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "person_0_0.csv"))
    g.updateVertices("post",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "post_0_0.csv"))
    g.updateVertices("organisation",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "organisation_0_0.csv"))
    g.updateVertices("place",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "place_0_0.csv"))
    g.updateVertices("tag",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "tag_0_0.csv"))
    g.updateVertices("tagClass",
      spark.read.format("csv").option("header","true").option("delimiter","|").load(inputPath + "tagclass_0_0.csv"))


    /* CSV files containing edges
     *  - comment_hasCreator_person_0_0.csv
     *  - comment_hasTag_tag_0_0.csv
     *  - comment_isLocatedIn_place_0_0.csv
     *  - comment_replyOf_comment_0_0.csv
     *  - comment_replyOf_post_0_0.csv
     *  - forum_containerOf_post_0_0.csv
     *  - forum_hasMember_person_0_0.csv
     *  - forum_hasModerator_person_0_0.csv
     *  - forum_hasTag_tag_0_0.csv
     *  - person_hasInterest_tag_0_0.csv
     *  - person_isLocatedIn_place_0_0.csv
     *  - person_knows_person_0_0.csv
     *  - person_likes_comment_0_0.csv
     *  - person_likes_post_0_0.csv
     *  - person_studyAt_organisation_0_0.csv
     *  - person_workAt_organisation_0_0.csv
     *  - post_hasCreator_person_0_0.csv
     *  - post_hasTag_tag_0_0.csv
     *  - post_isLocatedIn_place_0_0.csv
     *  - organisation_isLocatedIn_place_0_0.csv
     *  - place_isPartOf_place_0_0.csv
     *  - tag_hasType_tagclass_0_0.csv
     *  - tagclass_isSubclassOf_tagclass_0_0.csv
     */

    val comment_hasCreator_person_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "comment_hasCreator_person_0_0.csv")
                                            .withColumnRenamed("Comment.id", "comment_id")
                                            .withColumnRenamed("Person.id","person_id")
    g.updateEdges("comment", "hasCreator", "person", comment_hasCreator_person_DF)

      val comment_hasTag_tag_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "comment_hasTag_tag_0_0.csv")
                                            .withColumnRenamed("Comment.id", "comment_id")
                                            .withColumnRenamed("Tag.id","tag_id")
    g.updateEdges("comment", "hasTag", "tag", comment_hasTag_tag_DF)

    val comment_isLocatedIn_place_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "comment_isLocatedIn_place_0_0.csv")
                                            .withColumnRenamed("Comment.id", "comment_id")
                                            .withColumnRenamed("Place.id","place_id")
    g.updateEdges("comment", "isLocatedIn", "place", comment_isLocatedIn_place_DF)

    val comment_replyOf_comment_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "comment_replyOf_comment_0_0.csv")
                                            .withColumnRenamed("Comment.id0", "out_id")
                                            .withColumnRenamed("Comment.id1","in_id")
    g.updateEdges("comment", "replyOf", "comment", comment_replyOf_comment_DF)

    val comment_replyOf_post_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "comment_replyOf_post_0_0.csv")
                                            .withColumnRenamed("Comment.id", "comment_id")
                                            .withColumnRenamed("Post.id","post_id")
    g.updateEdges("comment", "replyOf", "post", comment_replyOf_post_DF)

    val forum_containerOf_post_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "forum_containerOf_post_0_0.csv")
                                            .withColumnRenamed("Forum.id", "forum_id")
                                            .withColumnRenamed("Post.id","post_id")
    g.updateEdges("forum", "containerOf", "post", forum_containerOf_post_DF)

    val forum_hasMember_person_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "forum_hasMember_person_0_0.csv")
                                            .withColumnRenamed("Forum.id", "forum_id")
                                            .withColumnRenamed("Person.id","person_id")
    g.updateEdges("forum", "hasMember", "person", forum_hasMember_person_DF)

    val forum_hasModerator_person_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "forum_hasModerator_person_0_0.csv")
                                            .withColumnRenamed("Forum.id", "forum_id")
                                            .withColumnRenamed("Person.id","person_id")
    g.updateEdges("forum", "hasModerator", "person", forum_hasModerator_person_DF)

    val forum_hasTag_tag_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "forum_hasTag_tag_0_0.csv")
                                            .withColumnRenamed("Forum.id", "forum_id")
                                            .withColumnRenamed("Tag.id","tag_id")
    g.updateEdges("forum", "hasTag", "tag", forum_hasTag_tag_DF)

    val person_hasInterest_tag_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_hasInterest_tag_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Tag.id","tag_id")
    g.updateEdges("person", "hasInterest", "tag", person_hasInterest_tag_DF)

    val person_isLocatedIn_place_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_isLocatedIn_place_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Place.id","place_id")
    g.updateEdges("person", "isLocatedIn", "place", person_isLocatedIn_place_DF)

    val person_knows_person_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_knows_person_0_0.csv")
                                            .withColumnRenamed("Person.id0", "out_id")
                                            .withColumnRenamed("Person.id1","in_id")
    g.updateEdges("person", "knows", "person", person_knows_person_DF)

    val person_likes_comment_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_likes_comment_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Comment.id","comment_id")
    g.updateEdges("person", "likes", "comment", person_likes_comment_DF)

    val person_likes_post_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_likes_post_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Post.id","post_id")
    g.updateEdges("person", "likes", "post", person_likes_post_DF)

    val person_studyAt_organisation_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_studyAt_organisation_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Organisation.id","organisation_id")
    g.updateEdges("person", "studyAt", "organisation", person_studyAt_organisation_DF)

    val person_workAt_organisation_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "person_workAt_organisation_0_0.csv")
                                            .withColumnRenamed("Person.id", "person_id")
                                            .withColumnRenamed("Organisation.id","organisation_id")
    g.updateEdges("person", "workAt", "organisation", person_workAt_organisation_DF)

    val post_hasCreator_person_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "post_hasCreator_person_0_0.csv")
                                            .withColumnRenamed("Post.id", "post_id")
                                            .withColumnRenamed("Person.id","person_id")
    g.updateEdges("post", "hasCreator", "person", post_hasCreator_person_DF)

    val post_hasTag_tag_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "post_hasTag_tag_0_0.csv")
                                            .withColumnRenamed("Post.id", "post_id")
                                            .withColumnRenamed("Tag.id","tag_id")
    g.updateEdges("post", "hasTag", "tag", post_hasTag_tag_DF)

    val post_isLocatedIn_place_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "post_isLocatedIn_place_0_0.csv")
                                            .withColumnRenamed("Post.id", "post_id")
                                            .withColumnRenamed("Place.id","place_id")
    g.updateEdges("post", "isLocatedIn", "place", post_isLocatedIn_place_DF)

    val organisation_isLocatedIn_place_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "organisation_isLocatedIn_place_0_0.csv")
                                            .withColumnRenamed("Organisation.id", "organisation_id")
                                            .withColumnRenamed("Place.id","place_id")
    g.updateEdges("organisation", "isLocatedIn", "place", organisation_isLocatedIn_place_DF)

    val place_isPartOf_place_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "place_isPartOf_place_0_0.csv")
                                            .withColumnRenamed("Place.id0", "out_id")
                                            .withColumnRenamed("Place.id1","in_id")
    g.updateEdges("place", "isPartOf", "place", place_isPartOf_place_DF)

    val tag_hasType_tagClass_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "tag_hasType_tagclass_0_0.csv")
                                            .withColumnRenamed("Tag.id", "tag_id")
                                            .withColumnRenamed("TagClass.id","tagClass_id")
    g.updateEdges("tag", "hasType", "tagClass", tag_hasType_tagClass_DF)

    val tagclass_isSubclassOf_tagclass_DF =  spark.read.format("csv").option("header","true").option("delimiter","|")
                                            .load(inputPath + "tagclass_isSubclassOf_tagclass_0_0.csv")
                                            .withColumnRenamed("TagClass.id0", "out_id")
                                            .withColumnRenamed("TagClass.id1","in_id")
    g.updateEdges("tagClass", "isSubclassOf", "tagClass", tagclass_isSubclassOf_tagclass_DF)

  }
}
