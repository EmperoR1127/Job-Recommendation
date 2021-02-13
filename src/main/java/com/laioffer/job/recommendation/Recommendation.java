package com.laioffer.job.recommendation;

import com.laioffer.job.db.MySQLConnection;
import com.laioffer.job.entity.Item;
import com.laioffer.job.external.GitHubClient;

import java.util.*;

public class Recommendation {
    public List<Item> recommendItems(String userId, double lat, double lon) {
        // Step 1, get all favorite item ids
        MySQLConnection connection = new MySQLConnection();
        Set<String> favoriteItemIds = connection.getFavoriteItemIds(userId);
        // Step 2, get all keywords, sort by count
        // {"software engineer": 6, "backend": 4, "san francisco": 3, "remote": 1}
        Map<String, Integer> allKeywords = new HashMap<>();
        for (String itemId : favoriteItemIds) {
            Set<String> keywords = connection.getKeywords(itemId);
            for (String keyword : keywords) {
                allKeywords.put(keyword, allKeywords.getOrDefault(keyword, 0) + 1);
            }
        }
        connection.close();
        
        List<Map.Entry<String, Integer>> keywordsList = new ArrayList<>(allKeywords.entrySet());
        keywordsList.sort((Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) ->
                Integer.compare(e2.getValue(), e1.getValue()));
        // Cut down search list only top 3
        if (keywordsList.size() > 3) {
            keywordsList = keywordsList.subList(0, 3);
        }
        // Step 3, search based on keywords, filter out favorite items
        GitHubClient client = new GitHubClient();
        List<Item> recommendItems = new ArrayList<>();
        Set<String> visitedItemIds = new HashSet<>();
        for (Map.Entry<String, Integer> keyword : keywordsList) {
            List<Item> items = client.search(lat, lon, keyword.getKey());
            for (Item item : items) {
                String itemId = item.getId();
                if (!favoriteItemIds.contains(itemId) && !visitedItemIds.contains(itemId)) {
                    recommendItems.add(item);
                    visitedItemIds.add(itemId);
                }
            }
        }
        
        return recommendItems;
    }
}











