/*
 * Copyright (c) 2015 PocketHub
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pockethub.android.ui.user;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.services.client.GithubListClient;
import com.alorma.github.sdk.services.orgs.GetOrgEventsClient;
import com.github.pockethub.android.accounts.AccountUtils;
import com.github.pockethub.android.core.PageIterator;
import com.github.pockethub.android.core.ResourcePager;

import java.util.List;

/**
 * Fragment to display an organization's news
 */
public class OrganizationNewsFragment extends UserNewsFragment {

    @Override
    protected ResourcePager<GithubEvent> createPager() {
        return new EventPager() {

            @Override
            public PageIterator<GithubEvent> createIterator(int page, int size) {
                return new PageIterator<>(new PageIterator.GitHubRequest<List<GithubEvent>>() {
                    @Override
                    public GithubListClient<List<GithubEvent>> execute(int page) {
                        String account = AccountUtils.getLogin(getActivity());
                        return new GetOrgEventsClient(account, org.login, page);
                    }
                }, page);
            }
        };
    }
}
