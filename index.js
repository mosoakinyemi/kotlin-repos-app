import React from "react";
import { AppRegistry, StyleSheet, Text, View, Image } from "react-native";

const HelloWorld = (props) => {
  return (
    <View style={styles.container}>
      <Image source={{ uri: props?.user_avatar }} style={styles.user_avatar} />
      <Text style={styles.repoTitle}>{props?.repo_title || "No Data"}</Text>
      <Text style={styles.repoStars}>Stars: {props?.stars}</Text>
    </View>
  );
};
var styles = StyleSheet.create({
  user_avatar: {
    height: 300,
    width: 300,
    borderRadius: 150,
    resizeMode: "cover",
    marginBottom: 20,
    alignSelf: "center",
  },
  repoStars: {
    fontSize: 15,
    textAlign: "center",
    margin: 10,
  },
  repoTitle: {
    fontSize: 20,
    textAlign: "center",
    fontWeight: "bold",
    margin: 10,
  },
  container: {
    flex: 1,
    width: "100%",
    justifyContent: "center",
    alignSelf: "center",
    backgroundColor: "#F5F5F5",
  },
});

AppRegistry.registerComponent("ReactNativeApp", () => HelloWorld);
